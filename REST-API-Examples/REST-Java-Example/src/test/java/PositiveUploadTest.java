import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.Base64;
import java.util.Date;

public class PositiveUploadTest {

    //Standardizing methods of delivery
    enum HTTP_METHOD {GET, POST, PUT, DELETE}

    //CONSTANTS

    private String testHeight = "175";
    private String testWeight = "73.2";


    private String authToken;
    private OkHttpClient webClient;
    private String authorizationBase64;
    private String appAuthorizationBase64;
    private AbstractMap.SimpleEntry<String, String> authTokenEntry;

    //ENDPOINTS
    private String userCreationUrl = "someUrl";
    private String uploadDataUrl = "someUrl1";
    private String downloadDataUrl = "someUrl2";

    //SECURITY CREDS
    private String authString = "someAuth";
    private String appAuthString = "someAppAuth";

    //Access credentials do not change, therefore we can sort out the formatting only once
    @Before
    public void setUpThePositiveTest() throws IOException{
        authorizationBase64    = "Basic " + new String(Base64.getEncoder().encode(authString.getBytes()));
        appAuthorizationBase64 = "Basic " + new String(Base64.getEncoder().encode(appAuthString.getBytes()));
        webClient = new OkHttpClient();
    }

    /*
    This Integration Test checks that User specific (hypothetical) data about Height and Weight, once uploaded,
    can be successfully retrieved unmodified
     */
    @Test
    public void check_Uploaded_HealthDataSample_Corresponds_ExactlyTo_Downloaded_Sample() {
        //Create a user and generate an authentication token
        authToken = generated_ThryveUser_AuthToken();
        //create a simple map entry for later access
        authTokenEntry = new AbstractMap.SimpleEntry<>("authenticationToken", authToken);
        //push data to Thryve server
        long uploadTStamp = upload_Thryve_UserData(authToken);
        System.out.println("We Uploaded data just after timestamp: -> "+uploadTStamp);
        //download the data we just uploaded
        String uData = download_ThryveUser_Data(authToken, uploadTStamp);
        System.out.println("Downloaded data: -> "+uData);
        String[] actualValues = extractTargetActualValues(uData);
        //Single gate assertion for this test to pass (Positive Assertion); all other assertions will fail the test
        Assert.assertTrue(actualValues[0].equals(testWeight) && actualValues[1].equals(testHeight));
    }

    /*
     UTILITY TEST HELPER METHODS
     */

    //Since a thryve user does not expire once created, it only needs to be performed once
    private String generated_ThryveUser_AuthToken(){
        String generatedAuthToken = "";
        RequestBody reqbody = RequestBody.create(null, new byte[0]);
        Request request = buildRequest("application/x-www-form-urlencoded", userCreationUrl, HTTP_METHOD.POST.toString(), reqbody, null);

        try {
            generatedAuthToken = webClient.newCall(request).execute().body().string();
        } catch (IOException e) {
            Assert.fail("We could not Generate a Thryve User: Closer inspection Stacktrace: "+e.getMessage());
        }
        System.out.println("Authentication Token : -> "+generatedAuthToken);
        return generatedAuthToken;
    }

    /*
    UPLOAD THRYVE USER DATA
     */
    private long upload_Thryve_UserData(String authToken){
        long timestampJustBeforeUpload = stampTimeBeforeUpload();
        String userData = "{\"height\": "+testHeight+",\"weight\": "+testWeight+",\"birthdate\": \"1974-09-14\",\"gender\": \"female\"}";
        RequestBody reqbody = RequestBody.create(MediaType.parse("application/json"), userData);
        Request request = buildRequest("application/json", uploadDataUrl, HTTP_METHOD.PUT.toString(), reqbody, authTokenEntry);

        try {
            Response response = webClient.newCall(request).execute();
            System.out.println("Response Code: ["+response.code()+"] : Data Upload Success: -> "+response.isSuccessful());
        } catch (IOException e) {
            Assert.fail("We could not Upload Thryve User Data: Closer inspection Stacktrace: "+e.getMessage());
        }
        return timestampJustBeforeUpload;
    }

    //Since a thryve user does not expire once created, it only needs to be performed once
    private String download_ThryveUser_Data(String authToken, long timeOfUpload){
        String downloadedData = "";

        String userData = "authenticationToken="+authToken+"&createdAfterUnix="+timeOfUpload;
        RequestBody reqbody = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), userData);
        Request request = buildRequest("application/x-www-form-urlencoded",downloadDataUrl,HTTP_METHOD.POST.toString(), reqbody, authTokenEntry);

        try {
            downloadedData = webClient.newCall(request).execute().body().string();
        } catch (IOException e) {
            Assert.fail("We could not Download Thryve User Data: Closer inspection Stacktrace: "+e.getMessage());
        }
        //remove the [] surrounding the json string
        return downloadedData.substring(1).replaceFirst(".$","");

    }

    /*
    This Utility method obtains a Unix Timestamp just before we uploaded data. It will be used to retrieve
    or download the user data at a later date
     */
    private long stampTimeBeforeUpload() {
        SimpleDateFormat sf = new SimpleDateFormat();
        sf.format(new Date());
        return sf.getCalendar().getTimeInMillis();
    }

    /*
    Utility function to extract the JSON Values from the downloaded data
    The order seems to be Weight first, then Height
     */
    private String[] extractTargetActualValues(String downloadData){
        JSONArray sourcesArray = new JSONObject(downloadData).getJSONArray("dataSources");
        JSONArray dataArray = sourcesArray.getJSONObject(0).getJSONArray("data");
        String[] actualValues = {dataArray.getJSONObject(0).getString("value"),
                                 dataArray.getJSONObject(1).getString("value")};
        return actualValues;
    }

    /*
        Generic method to build an HTTP Request of different types
     */
    private Request buildRequest(String contentType, String endpoint, String reqMethod, RequestBody reqBody,
                                 AbstractMap.SimpleEntry<String, String> authTokenE){
        Request.Builder requestBuilder = new Request.Builder();
        if(authTokenE != null) {
            requestBuilder.addHeader("authenticationToken", authTokenE.getValue());
        }
        requestBuilder.url(endpoint)
                .addHeader("Content-Type", contentType)
                .addHeader("Authorization", authorizationBase64)
                .addHeader("AppAuthorization", appAuthorizationBase64)
                .method(reqMethod, reqBody);
        return requestBuilder.build();
    }



}
