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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class OWASPShop_ItemPaginationTest {


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
    public void check_Pagination_Contains_Different_Items()
    {
        try {
            Thread.sleep(3000);
            List<WebElement> p1Items = getAllWebElements("Printing All Page 1 Items out ...\n");
            Thread.sleep(3000);
            clickNextPageButton();
            Thread.sleep(3000);
            List<WebElement> p2Items = getAllWebElements("Printing All Page 2 Items out ...\n");
            Thread.sleep(3000);
            clickNextPageButton();
            Thread.sleep(3000);
            List<WebElement> p3Items = getAllWebElements("Printing All Page 3 Items out ...\n");

            List<WebElement> twoPageItemList = new ArrayList<WebElement>();
            twoPageItemList.addAll(p2Items);
            twoPageItemList.addAll(p3Items);
            for (WebElement pElement : p1Items) {
                assertFalse("Some elements on Page 1 appeared in the other pages", twoPageItemList.contains(pElement));
            }
            for  (WebElement pElement : p2Items) {
                assertFalse("Some elements on Page 2 appeared in Page 3", p3Items.contains(pElement));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            driver.manage().deleteAllCookies();
            cleanUp();
        }
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

    //Click the Next-Page Button at the bottom in order to scroll to the next page and display new items
    private static void clickNextPageButton() {
        clickOnButtonOrLink("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-search-result/div/div/mat-paginator/div/div/div[2]/button[2]");
    }

    //Try and dismiss the smaller popup at the bottom of the screen
    private static void clickDimissButton(){
        clickOnButtonOrLink("/html/body/div[1]/div/a");
    }

    //Try and dismiss the welcome popup on the main page
    private static void clickDismissJuiceShopWelcome(){
        clickOnButtonOrLink("//*[@id=\"mat-dialog-0\"]/app-welcome-banner/div/div[2]/button[2]");
    }

    //Click on a WebElement
    private static void clickOnButtonOrLink(String xpathExpression) {
        WebElement mapObject = driver.findElement(By.xpath(xpathExpression));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", mapObject);
    }

}
