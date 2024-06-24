package datarade;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OWASPShop_ItemPerPageTest extends OWASPSHOP_JuicyTestSuite{

    //static WebDriver driver;

    public OWASPShop_ItemPerPageTest(){
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
    public void verify_Lowest_Items_Per_Page_Count()
    {
        try {
            Thread.sleep(3000);
            List<WebElement> allPageItems = getAllWebElements("Printing All First Page Items ...\n");
            Thread.sleep(3000);
            assertEquals("There are meant to be 12 Items on the Landing Page", 12, allPageItems.size());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            driver.manage().deleteAllCookies();
            cleanUp();
        }
    }

    /*
    Test to check that the Option for 24 items per page
   */
    @Test
    public void verify_Average_Number_Of_Items_Per_Page_Count()
    {
        try {
            Thread.sleep(3000);
            selectFromDropdown("24");
            Thread.sleep(3000);
            List<WebElement> allPageItems = getAllWebElements("Printing All First Page Items ...\n");
            Thread.sleep(3000);
            assertEquals("There are meant to be 24 Items on the Landing Page", 24, allPageItems.size());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            driver.manage().deleteAllCookies();
            cleanUp();
        }
    }

}
