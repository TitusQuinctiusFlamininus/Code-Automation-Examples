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

public class OWASPShop_ItemPerPageTest {



    static WebDriver driver;


    /**
     * Setup of all integration tests.
     */
    @Before
    public  void setUp()
    {
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();

        synchronized (this) {
            System.out.println("Starting Chrome Driver ...");
            try {

                System.out.println("Chrome Driver Started ...");
                System.out.println("Deleting Cookies ...");
                driver.manage().deleteAllCookies();
                System.out.println("Cookies Deleted ...");
                Thread.sleep(3000);
                driver.get("http://localhost:3000");
                Thread.sleep(3000);
                clickDimissButton();
                Thread.sleep(3000);
                clickDismissJuiceShopWelcome();
                Thread.sleep(3000);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @After
    public void cleanUp()
    {
        driver.quit();
    }


    @Ignore
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

    //Click on a WebElement
    private static void clickOnButtonOrLink(String xpathExpression) {
        WebElement mapObject = driver.findElement(By.xpath(xpathExpression));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", mapObject);
    }

    //Try and dismiss the smaller popup at the bottom of the screen
    private static void clickDimissButton(){
        clickOnButtonOrLink("/html/body/div[1]/div/a");
    }

    //Try and dismiss the welcome popup on the main page
    private static void clickDismissJuiceShopWelcome(){
        clickOnButtonOrLink("//*[@id=\"mat-dialog-0\"]/app-welcome-banner/div/div[2]/button[2]");
    }

    private static void selectFromDropdown(String selection) {
        driver.findElement(By.id("mat-select-0")).click();
        driver.findElement(By.xpath("//span[contains(text(),'"+selection+"')]")).click();
    }

    //Get all the Items on the Page as a list; Print them out to standard output before returning the list
    private static List<WebElement> getAllWebElements(String log) {
        System.out.println(log);
        System.out.println("----------------------------------");
        List<WebElement> pageItems = driver.findElements(By.className("item-name"));
        for (WebElement element : pageItems) {
            System.out.println(element.getText());
        }
        System.out.println("----------------------------------");
        System.out.println("\n");
        return pageItems;
    }

}
