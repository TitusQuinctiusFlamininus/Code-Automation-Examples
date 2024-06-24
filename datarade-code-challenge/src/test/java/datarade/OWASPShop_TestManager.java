package datarade;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OWASPShop_TestManager {

     WebDriver driver;

    public OWASPShop_TestManager(WebDriver driver){
        this.driver = driver;
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

    //Try and dismiss the smaller popup at the bottom of the screen
    protected  void clickDimissButton(){
        clickOnButtonOrLink("/html/body/div[1]/div/a");
    }

    //Try and dismiss the welcome popup on the main page
    protected  void clickDismissJuiceShopWelcome(){
        clickOnButtonOrLink("//*[@id=\"mat-dialog-0\"]/app-welcome-banner/div/div[2]/button[2]");
    }

    protected  void selectFromDropdown(String selection) {
        driver.findElement(By.id("mat-select-0")).click();
        driver.findElement(By.xpath("//span[contains(text(),'"+selection+"')]")).click();
    }

    //Click the Next-Page Button at the bottom in order to scroll to the next page and display new items
    protected void clickNextPageButton() {
        clickOnButtonOrLink("/html/body/app-root/div/mat-sidenav-container/mat-sidenav-content/app-search-result/div/div/mat-paginator/div/div/div[2]/button[2]");
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
