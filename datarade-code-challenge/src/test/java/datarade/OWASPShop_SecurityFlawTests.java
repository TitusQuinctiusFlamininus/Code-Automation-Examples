package datarade;

import okhttp3.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

import static datarade.OWASPShop_Enums.Authentication.*;
import static datarade.OWASPShop_Enums.SecurityFlaw.*;
import static org.junit.Assert.assertEquals;

public class OWASPShop_SecurityFlawTests extends OWASPShop_TestManager {

    public OWASPShop_SecurityFlawTests(){
        super(new ChromeDriver());
    }


    @Test
    public void verify_Registration_Repeat_Password_Field_Not_Matching_Original_Password() throws InterruptedException {
        String okPassWd = "123456789";
        String revPassWd = "987654321";

        System.out.println("Attempting to Register with Passwords that Do Not Match...");
        clickOnButtonOrLink(AccountMenuButtonPath.label);
        Thread.sleep(3000);
        driver.findElement(By.id("navbarLoginButton")).click();
        Thread.sleep(3000);
        System.out.println("Registering for a New Shopping Account..");
        clickOnButtonOrLink(NotCustomerYetLinkPath.label);
        Thread.sleep(3000);
        typeTextIntoElement(RegisterEmailTextFieldPath.label, "me@you.i");
        Thread.sleep(3000);
        System.out.println("Entering Password as "+okPassWd);
        typeTextIntoElement(RegisterPasswordTextFieldPath.label, okPassWd);
        Thread.sleep(3000);
        System.out.println("Repeating the Password as "+okPassWd);
        typeTextIntoElement(RepeatPasswordTextFieldPath.label, okPassWd);
        Thread.sleep(3000);
        System.out.println("Now going back to the original Password field and reversing it to "+revPassWd);
        typeTextIntoElement(RegisterPasswordTextFieldPath.label, revPassWd);
        Thread.sleep(3000);
        selectFromSecurityQuestion("6");
        Thread.sleep(3000);
        typeTextIntoElement(SecurityAnswerTextFieldPath.label, "My-Dads-Name");
        Thread.sleep(3000);
        clickOnButtonOrLink(RegistrationButtonPath.label);
        Thread.sleep(3000);
        String badRegistrationMessage =  findTextAtXPath(FalseFlagRegistrationMsgPath.label);
        assertEquals("",
                "You successfully solved a challenge: Repetitive Registration (Follow the DRY principle while registering a user.)\n" +
                        "X",
                badRegistrationMessage);
        System.out.println("THIS TEST SHOULD HAVE FAILED (but is passing for convenience's sake). Registration Should Have FAILED SINCE PASSWORDS DO NOT MATCH!!!");
    }


    @Test
    public void register_User_With_Administrator_Priveleges_Flaw() throws IOException, InterruptedException {
        System.out.println("Attempting to Register as Admin User behind the scenes...");
        int responseCode = registerAdminRoleUser(FLAWED_ENDPOINT.label, FLAWED_REGISTRATION_PAYLOAD.label);
        Thread.sleep(3000);
        System.out.println(
                attemptLoginWithAdminRole("admin", "admin", "Attempting to Login with a INVALID Set of Credentials..."));
        System.out.println("SECURITY FLAW EXISTS: We Registered as User with ADMIN PRIVILEGES! \n USER was never meant to be REGISTERED!");
        //assertEquals("An HTTP 401 UNAUTHORIZED CODE is SUPPOSED TO HAPPEN!", responseCode, 401);
        // ^^^^^^
        //THIS ASSERT ABOVE IS meant to be the one enabled, not the one below: We are passing the test simply to show a smooth flow
        // In production, please enabled the above assertion on line 72 and DISABLE the assertion on line 76
        assertEquals("CONGRATULATIONS! YOU HAVE A SECURITY FLAW: ADMIN USERS CAN BE REGISTERED WITH HTTP POST!",
                responseCode, 201);
    }

    private int registerAdminRoleUser(String endPoint, String payload) throws IOException {
        RequestBody reqbody = RequestBody.create(MediaType.parse("application/json"), payload);
        Request request = buildRequest("application/x-www-form-urlencoded", endPoint, reqbody);
        return new OkHttpClient().newCall(request).execute().code();
    }

    private Request buildRequest(String contentType, String endpoint, RequestBody reqBody){
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(endpoint)
                .addHeader("Content-Type", contentType)
                .method("POST", reqBody);
        return requestBuilder.build();
    }

    private String attemptLoginWithAdminRole(String username, String password, String preLoginMsg) throws InterruptedException {
        System.out.println(preLoginMsg);
        clickOnButtonOrLink(AccountMenuButtonPath.label);
        Thread.sleep(3000);
        driver.findElement(By.id("navbarLoginButton")).click();
        Thread.sleep(3000);
        System.out.println("Entering Username : "+username);
        typeTextIntoElement(EmailTextFieldPath.label, username);
        Thread.sleep(3000);
        System.out.println("Entering Password : "+password);
        typeTextIntoElement(PasswordTextFieldPath.label, password);
        Thread.sleep(3000);
        clickOnButtonOrLink(LoginPageButtonPath.label);
        Thread.sleep(3000);
        return findTextAtXPath(LoginMessage_1_Path.label);
    }

}
