package datarade;


import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;


public class OWASPShop_ItemPaginationTest extends OWASPShop_TestManager {

    public OWASPShop_ItemPaginationTest(){
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

}
