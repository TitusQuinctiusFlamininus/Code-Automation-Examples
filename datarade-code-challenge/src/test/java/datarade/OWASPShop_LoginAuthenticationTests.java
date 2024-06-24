package datarade;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

import static datarade.OWASPShop_Enums.Authentication.*;
import static org.junit.Assert.assertEquals;

public class OWASPShop_LoginAuthenticationTests extends OWASPShop_TestManager {

    private Set<String> headerTextList;

    public OWASPShop_LoginAuthenticationTests(){
        super(new ChromeDriver());
    }

    /**
     * Setup of all integration tests.
     */
    @Before
    public  void setUp()
    {
        setupTheTest();
    }

    @After
    public void cleanUp()
    {
        driver.quit();
    }

    @Test
    public void random_Username_Password_Combination_Fails() throws InterruptedException {
        clickOnButtonOrLink(AccountMenuButtonPath.label);
        Thread.sleep(3000);
        driver.findElement(By.id("navbarLoginButton")).click();
        Thread.sleep(3000);
        typeTextIntoElement(EmailTextFieldPath.label, "sdfdf@sudfhe.com");
        Thread.sleep(3000);
        typeTextIntoElement(PasswordTextFieldPath.label, "ut43tfe32#74fgh+ads");
        Thread.sleep(3000);
        clickOnButtonOrLink(LoginPageButtonPath.label);
        Thread.sleep(3000);
        assertEquals("Invalid email or password.", findTextAtXPath(AuthAttemptMessagePath.label));
    }

}
