package datarade;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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
    public void verify_shift_To_german_from_english_changes_text()
    {
        try {
            Thread.sleep(3000);
            List<WebElement> englishLanguageItems = getAllWebElements("Printing First Page Items (In English)...\n");
            Thread.sleep(3000);
            selectLanguageOfChoice("mat-radio-6"); //apparently german choice
            Thread.sleep(3000);
            driver.navigate().refresh(); //reload the page to make item labels appear in german
            Thread.sleep(3000);
            List<WebElement> germanLanguageItems = getAllWebElements("Printing First Page Items (in German)...\n");
            Thread.sleep(3000);

            for (WebElement eElement : englishLanguageItems) {
                assertFalse("Page is meant to be in German but some item labels are still in english!",
                        germanLanguageItems.contains(eElement));
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
