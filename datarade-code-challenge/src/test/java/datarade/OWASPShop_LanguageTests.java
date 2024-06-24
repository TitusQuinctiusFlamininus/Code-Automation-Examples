package datarade;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class OWASPShop_LanguageTests extends OWASPShop_TestManager {

    public OWASPShop_LanguageTests(){
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


    /*
    Check that the header of the page as well as the item labels change
    when one shifts from english to italian
     */
    @Test
    public void verify_English_German_Or_French_ItemsLabels_Do_Not_Appear_In_Each_Others_Lists()
    {
        try {
            //Default language is english, so let's just get the english item labels immediately
            Thread.sleep(3000);
            List<WebElement> englishLanguageItems = getAllWebElements("Printing First Page Items (In English)...\n");
            Thread.sleep(3000);
            selectLanguageOfChoice("mat-radio-6"); // german language choice
            Thread.sleep(3000);
            driver.navigate().refresh(); //reload the page to make item labels appear in german
            Thread.sleep(3000);
            List<WebElement> germanLanguageItems = getAllWebElements("Printing First Page Items (in German)...\n");
            Thread.sleep(3000);
            selectLanguageOfChoice("mat-radio-10"); // french language choice
            Thread.sleep(3000);
            driver.navigate().refresh(); //reload the page to make item labels appear in french
            Thread.sleep(3000);
            List<WebElement> frenchLanguageItems = getAllWebElements("Printing First Page Items (in French)...\n");
            Thread.sleep(3000);
            List<WebElement> gAndF_ItemList = new ArrayList<WebElement>();
            gAndF_ItemList.addAll(germanLanguageItems);
            gAndF_ItemList.addAll(frenchLanguageItems);

            for (WebElement eElement : englishLanguageItems) {
                assertFalse("Some Items Labelled in English Appear in the German or French Lists!",
                        gAndF_ItemList.contains(eElement));
            }
            for (WebElement gElement : germanLanguageItems) {
                assertFalse("Some Items Labelled in German Appear in the List of French Items!",
                        frenchLanguageItems.contains(gElement));
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
