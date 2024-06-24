package datarade;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

import static datarade.OWASPShop_Enums.Authentication.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OWASPShop_LoginAuthenticationTests extends OWASPShop_TestManager {

    private Set<String> headerTextList;

    public OWASPShop_LoginAuthenticationTests(){
        super(new ChromeDriver());
    }

    @Test
    public void verify_Invalid_Username_Password_Combination_Fails() throws InterruptedException {
        System.out.println("Attempting to Login with an Invalid Set of Credentials...");
        clickOnButtonOrLink(AccountMenuButtonPath.label);
        Thread.sleep(3000);
        driver.findElement(By.id("navbarLoginButton")).click();
        Thread.sleep(3000);
        System.out.println("Entering Username..");
        typeTextIntoElement(EmailTextFieldPath.label, "sdfdf@sudfhe.com");
        Thread.sleep(3000);
        System.out.println("Entering Password..");
        typeTextIntoElement(PasswordTextFieldPath.label, "ut43tfe32#74fgh+ads");
        Thread.sleep(3000);
        clickOnButtonOrLink(LoginPageButtonPath.label);
        Thread.sleep(3000);
        System.out.println("Login Should have failed.");
        assertEquals("Invalid email or password.", findTextAtXPath(AuthAttemptMessagePath.label));
    }

    @Test
    public void verify_Valid_Username_Password_Combination_Succeeds() throws InterruptedException {
        System.out.println("Attempting to Login with a Valid Set of Credentials...");
        clickOnButtonOrLink(AccountMenuButtonPath.label);
        Thread.sleep(3000);
        driver.findElement(By.id("navbarLoginButton")).click();
        Thread.sleep(3000);
        System.out.println("Entering Username..");
        typeTextIntoElement(EmailTextFieldPath.label, ValidUsername.label);
        Thread.sleep(3000);
        System.out.println("Entering Password..");
        typeTextIntoElement(PasswordTextFieldPath.label, ValidPassword.label);
        Thread.sleep(3000);
        clickOnButtonOrLink(LoginPageButtonPath.label);
        Thread.sleep(3000);
        System.out.println("Login Should have Succeeded!");
        assertEquals("Initial Success Message does not match up!", SuccessLoginMessage_1.label, findTextAtXPath(SuccessLoginMessage_1_Path.label));
        assertEquals("Second Success Message does not match up!", SuccessLoginMessage_2.label, findTextAtXPath(SuccessLoginMessage_2_Path.label));
    }

}
