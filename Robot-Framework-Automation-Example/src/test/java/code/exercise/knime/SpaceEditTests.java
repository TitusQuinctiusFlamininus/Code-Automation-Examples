package code.exercise.knime;

import okhttp3.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Base64;


/**
 * Automated Test to Check KNIME Spaces Can be Successfully Created
 * for authenticated users
 */
public class SpaceEditTests {

    enum HTTP_METHOD {POST, PUT, DELETE}

    private OkHttpClient webClient;
    private String contentType = "application/x-www-form-urlencoded";

    //Credentials
    private String authStringEncoded;
    private String authToken;
    private RequestBody reqbody;
    private AbstractMap.SimpleEntry<String, String> authTokenEntry;

    //ENDPOINTS
    //?? Not sure what the correct endpoint is ??
    private String authLoginEP = "https://auth.hub.knime.com/auth/realms/knime/";
    //These space endpoints have these formats, but we need the auth token first
    private String createSpaceEP = "https://<knime-server>/knime/webportal/space/<path>?exec&knime:access_token=<token>";
    private String deleteSpaceEP = "https://<knime-server>/knime/webportal/space/<path>?exec&knime:access_token=<token>";


    /*
-------------------------------------------------------------------
                    Test Methods
-------------------------------------------------------------------
 */

    // This is executed before any test starts
    @Before
    public void startTheTest()
    {
        webClient = new OkHttpClient();
        //Get the Username and Password passed in as System Variables
        String authString = System.getProperty("username")+":"+System.getProperty("password");
        authStringEncoded = "Basic " + new String(Base64.getEncoder().encode(authString.getBytes()));
        reqbody = RequestBody.create(null, new byte[0]);
        //Logging in the KNIME User is a prerequisite for any test passing
        authToken = authenticateUser();
        authTokenEntry = new AbstractMap.SimpleEntry<>("authenticationToken", authToken);
    }

    @Ignore
    //Test to check that a User Space can be created successfully
    @Test
    public void check_KNIME_Space_Can_Be_Created_Successfully_For_Authenticated_User()
    {
        Assert.assertEquals(200, manipulateUserSpace(createSpaceEP, HTTP_METHOD.PUT));
        //As a teardown for this test, we need to remove the space created
        manipulateUserSpace(deleteSpaceEP, HTTP_METHOD.DELETE);
    }

    @Ignore
    //Test to now check that a user space can be deleted successfully
    @Test
    public void check_KNIME_Space_Can_Be_Deleted_Successfully_For_Authenticated_User()
    {
        //First create a user space
        manipulateUserSpace(createSpaceEP, HTTP_METHOD.PUT);
        //then delete it
        Assert.assertEquals(200, manipulateUserSpace(deleteSpaceEP, HTTP_METHOD.DELETE));
    }

/*
-------------------------------------------------------------------
                    Utility Helper Methods
-------------------------------------------------------------------
 */
    //Method to login the user: It returns an Authentication Token which can then be used in later
    //requests
    private String authenticateUser(){
        String generatedAuthToken = "";
        Request request = buildRequest( authLoginEP, HTTP_METHOD.POST.toString(), reqbody, null);

        try {
            generatedAuthToken = webClient.newCall(request).execute().body().string();
        } catch (IOException e) {
            Assert.fail("The KNIME User Could Not Be Authenticated: Closer inspection Stacktrace: "+e.getMessage());
        }
        System.out.println("Authentication KNIME Token : -> "+generatedAuthToken);
        return generatedAuthToken;
    }

    private long manipulateUserSpace(String theEndpoint, HTTP_METHOD theHTTPMethod){
        Request request = buildRequest(theEndpoint, theHTTPMethod.toString(), reqbody, authTokenEntry);
        int responseCode  = 0;
        try {
            Response response = webClient.newCall(request).execute();
            responseCode  = response.code();
            System.out.println("Response Code: ["+response.code()+"] : User Space Manipulation Success: -> "+response.isSuccessful());
        } catch (IOException e) {
            Assert.fail("We could not successfully manipulate the user space: Closer inspection Stacktrace: "+e.getMessage());
        }
        return responseCode;
    }

    /*
       Generic utility method to build an HTTP Request of different types
    */
    private Request buildRequest(String endpoint, String reqMethod, RequestBody reqBody,
                                 AbstractMap.SimpleEntry<String, String> authTokenE){
        Request.Builder requestBuilder = new Request.Builder();
        //If it is null, it means we don't have any Authentication token yet, it has to be an authentication req
        if(authTokenE != null) {
            requestBuilder.addHeader("authenticationToken", authTokenE.getValue());
        }
        requestBuilder.url(endpoint)
                .addHeader("Content-Type", contentType)
                .addHeader("Authorization", authStringEncoded)
                .method(reqMethod, reqBody);
        return requestBuilder.build();
    }
}

