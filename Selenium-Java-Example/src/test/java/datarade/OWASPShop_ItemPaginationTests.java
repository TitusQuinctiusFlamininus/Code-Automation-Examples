package datarade;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertFalse;


/*
This class represents tests that deal with paging through the various item pages of the
website. The number of pages to page (or scroll) through will depend on the maximum number
of items being displayed per page (adjustable at the bottom of the page)
 */
public class OWASPShop_ItemPaginationTests extends OWASPShop_TestManager {

    public OWASPShop_ItemPaginationTests(){
        super(new ChromeDriver());
    }

    /*
    Page through the different pages and gather the names of the items on different pages.
    Then check to see if they are unique across the website. The default language was used (english),
    although this could have been done with any language.
    */
    @Test
    public void check_Pagination_Contains_Different_Items() throws InterruptedException {
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

}
