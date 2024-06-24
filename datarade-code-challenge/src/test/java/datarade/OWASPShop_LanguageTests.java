package datarade;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static datarade.OWASPShop_Enums.Language.*;
import static datarade.OWASPShop_Enums.Navigation.HeaderTextPath;

import static org.junit.Assert.*;

public class OWASPShop_LanguageTests extends OWASPShop_TestManager {

    private Set<String> headerTextList;

    public OWASPShop_LanguageTests(){
        super(new ChromeDriver());
    }

    @Test
    public void verify_page_title_language_change_for_first_16_language_change() throws InterruptedException {
        headerTextList = new HashSet<String>();
        //Cycle through the languages, changing them in turn and gathering the header texts in different languages
        for(int langIndex=1; langIndex < 17; langIndex++){
            selectLanguageOfChoice(String.valueOf(langIndex)); // some language choice
            Thread.sleep(2000);
            driver.navigate().refresh(); //reload the page
            Thread.sleep(2000);
            String headerText = findTextAtXPath(HeaderTextPath.label);
            System.out.println("Header :> "+headerText);
            getHeaderTextList().add(headerText);
            Thread.sleep(2000);
        }
        assertTrue("All Language Title Headers are Unique in their own Translations.", true);
    }


    /*
    Check that the Item Labels of the page change
    when one shifts from english to german, and then to french;
    Also making sure that no item labels in one language text form, appear
    in the list of another (since they would presumably be translated into the
    respective language)
     */

    @Test
    public void verify_English_German_Or_French_ItemsLabels_Do_Not_Appear_In_Each_Others_Lists()
    {
        try {
            //Default language is english, so let's just get the english item labels immediately
            List<WebElement> englishLanguageItems = getAllWebElements("Printing First Page Items (In English)...\n");
            Thread.sleep(3000);
            selectLanguageOfChoice(GERMAN.label); // german language choice
            Thread.sleep(3000);
            driver.navigate().refresh(); //reload the page to make item labels appear in german
            Thread.sleep(3000);
            List<WebElement> germanLanguageItems = getAllWebElements("Printing First Page Items (in German)...\n");
            Thread.sleep(3000);
            selectLanguageOfChoice(FRENCH.label); // french language choice
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


    public Set<String> getHeaderTextList() {
        return headerTextList;
    }

    public void setHeaderTextList(Set<String> headerTextList) {
        this.headerTextList = headerTextList;
    }
}
