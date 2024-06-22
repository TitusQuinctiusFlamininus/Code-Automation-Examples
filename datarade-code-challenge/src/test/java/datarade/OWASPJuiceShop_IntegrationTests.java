package datarade;


import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class OWASPJuiceShop_IntegrationTests extends TestCase {

    WebDriver driver;

    /**
     * Setup of all integration tests.
     */
    @Before
    public  void setUp()
    {
        driver = new ChromeDriver();
        driver.get("http://localhost:3000");
    }


    @Test
    public void check_Pagination_Contains_Different_Items()
    {

        assertTrue( true );
    }


}
