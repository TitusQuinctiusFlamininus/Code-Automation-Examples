package datarade;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.Assert.*;

public class OWASPShop_ItemPerPageTests extends OWASPShop_TestManager {

    public OWASPShop_ItemPerPageTests(){
        super(new ChromeDriver());
    }

    /*
   Test to check that the Option for 12 items per page means that the first page has 12 items,
   the second page also has 12 and the last page has 11 items
  */

    @Test
    public void verify_Lowest_Items_Per_Page_Count_Correct_On_Every_Page()
    {
        try {
            Thread.sleep(3000);
            List<WebElement> firstPageItems = getAllWebElements("Printing First Page Items ...\n");
            Thread.sleep(3000);
            clickNextPageButton(); //Go to the second page
            Thread.sleep(3000);
            List<WebElement> secondPageItems = getAllWebElements("Printing Second Page Items ...\n");
            Thread.sleep(3000);
            clickNextPageButton(); //Go to the last page
            Thread.sleep(3000);
            List<WebElement> lastPageItems = getAllWebElements("Printing Last Page Items ...\n");
            Thread.sleep(3000);
            assertEquals("There are meant to be 12 Items on the Landing Page", 12, firstPageItems.size());
            assertEquals("There are meant to be 12 Items on the Second Page", 12, secondPageItems.size());
            assertEquals("There are meant to be 12 Items on the Last Page", 11, lastPageItems.size());
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
    Test to check that the Option for 24 items per page means that the first page has 24 items
    and the last page has 11, making a total of 35 Store items
   */

    @Test
    public void verify_Average_Number_Of_Items_Per_Page_Count_Correct_Count_On_Every_Page()
    {
        try {
            Thread.sleep(3000);
            selectNumberOfItemsPerPage("24");
            Thread.sleep(3000);
            List<WebElement> allFirstPageItems = getAllWebElements("Printing All First Page Items ...\n");
            Thread.sleep(3000);
            clickNextPageButton(); //Go to the last page
            Thread.sleep(3000);
            List<WebElement> allLastPageItems = getAllWebElements("Printing All Last Page Items ...\n");
            assertEquals("There are meant to be 24 Items on the Landing Page", 24, allFirstPageItems.size());
            assertEquals("There are meant to be 12 Items on the Last Page", 11, allLastPageItems.size());
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
  Test to check that the Option for 24 items per page means that the first page has 24 items
  and the last page has 11, making a total of 35 Store items
 */

    @Ignore
    @Test
    public void verify_Highest_Number_Of_Items_Per_Page_Count_Correct_Count_On_Every_Page()
    {
        try {
            Thread.sleep(3000);
            selectNumberOfItemsPerPage("36");
            Thread.sleep(3000);
            List<WebElement> allPageItems = getAllWebElements("Printing All Landing Page Items ...\n");
            Thread.sleep(3000);
            assertEquals("There are meant to be 35 Items on the Landing Page", 35, allPageItems.size());
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
