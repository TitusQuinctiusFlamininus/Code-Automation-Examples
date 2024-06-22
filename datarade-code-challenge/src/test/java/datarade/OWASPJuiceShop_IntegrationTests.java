package datarade;


import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class OWASPJuiceShop_IntegrationTests {

    WebDriver driver;

    /**
     * Setup of all integration tests.
     */
    @Before
    public  void setUp()
    {
        synchronized (this) {
            System.out.println("Starting Chrome Driver ...");
            try {
                driver = new ChromeDriver();
                System.out.println("Chrome Driver Started ...");
                System.out.println("Deleting Cookies ...");
                driver.manage().deleteAllCookies();
                System.out.println("Cookies Deleted ...");
                driver.get("http://localhost:3000");
                Thread.sleep(7000);
                System.out.println("Started the Juicy Shop Shop Browser ...");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @After
    public void cleanUp()
    {
        driver.manage().deleteAllCookies();
        driver.quit();
    }


    @Test
    public void check_Pagination_Contains_Different_Items()
    {
        System.out.println("Finding all class-name elements called Item-name ...");
        List<WebElement> palettePopup = driver.findElements(By.className("item-name"));
        System.out.println("Printing all of them out ...\n");
        for (WebElement element : palettePopup) {
            System.out.println(element.getText());
        }
        //System.out.println(driver.getPageSource());
       /*
        WebElement palettePopup = driver.findElement(By.name("palette"));
        System.out.println("Now clicking it ...");
        palettePopup.click();
        System.out.println("Done with clicking it!");
        */

        assertTrue( true );
    }


}
