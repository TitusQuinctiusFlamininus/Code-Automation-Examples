package datarade;


import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static datarade.OWASPShop_Enums.Navigation.*;

public class OWASPShop_TestManager {

     WebDriver driver;

    public OWASPShop_TestManager(WebDriver driver){
        this.driver = driver;
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

    //SETUP
    protected void setupTheTest() {
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

    //Click on a WebElement
    protected void clickOnButtonOrLink(String xpathExpression) {
        WebElement mapObject = this.driver.findElement(By.xpath(xpathExpression));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", mapObject);
    }

    //Type Text into a WebElement
    protected void typeTextIntoElement(String xpathExpression, String text) {
        WebElement mapObject = this.driver.findElement(By.xpath(xpathExpression));
        mapObject.sendKeys(text);
    }

    //Return on text on a WebElement at a specific xpath location
    protected String findTextAtXPath(String xpathExpression) {
        return this.driver.findElement(By.xpath(xpathExpression)).getText();
    }

    //Try and dismiss the smaller popup at the bottom of the screen
    protected  void clickDimissButton(){
        clickOnButtonOrLink(BottomPopupButtonPath.label);
    }

    //Try and dismiss the welcome popup on the main page
    protected  void clickDismissJuiceShopWelcome(){
        clickOnButtonOrLink(WelcomePopupButtonPath.label);
    }

    protected  void selectNumberOfItemsPerPage(String selection) {
        driver.findElement(By.id("mat-select-0")).click();
        driver.findElement(By.xpath("//span[contains(text(),'"+selection+"')]")).click();
    }

    protected  void selectLanguageOfChoice(String selection) throws InterruptedException {
        clickOnButtonOrLink(LanguageMenuButtonPath.label);
        Thread.sleep(3000);
        driver.findElement(By.id("mat-radio-"+selection)).click();

    }

    //Click the Next-Page Button at the bottom in order to scroll to the next page and display new items
    protected void clickNextPageButton() {
        clickOnButtonOrLink(NextPageNavigationButtonPath.label);
    }

    //Get all the Items on the Page as a list; Print them out to standard output before returning the list
    protected List<WebElement> getAllWebElements(String log) {
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
