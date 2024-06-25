package datarade;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

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


}
