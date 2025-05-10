/*

System Requirements: https://kata-log.rocks/unusual-spending-kata
 */

import java.util.HashMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
/**
 *
 * @author Ngumbah.Nyika
 */
public class UnusualSpendingEmailTests {
    
    private HashMap<Category, Double> unusualSpendCategories;
    private EmailProcessor testSubject;
    
    @BeforeEach
    public void setUp() {
        unusualSpendCategories = new HashMap();
    }
    
    //Email Header (Subject Line) Tests
    @Test
    public void check_email_header_amount_when_no_high_spending_occurred(){
        testSubject = new EmailProcessor(unusualSpendCategories);
        assertEquals("No Unusual spending detected!", testSubject.getEmailHeader());
    }
    
     @Test
    public void check_email_header_amount_when_total_amount_spending_for_a_category_is_zero(){
        unusualSpendCategories.put(Category.Travel, 0.0);
        testSubject = new EmailProcessor(unusualSpendCategories);
        assertEquals("No Unusual spending detected!", testSubject.getEmailHeader());
    }
    
    @Test
    public void check_email_header_amount_when_only_dining_category_exists(){
        unusualSpendCategories.put(Category.Dining, 10.00);
        testSubject = new EmailProcessor(unusualSpendCategories);
        assertEquals("Unusual spending of $10 detected!", testSubject.getEmailHeader());
    }
    
     @Test
    public void check_email_header_amount_when_different_travel_category_exists(){
        double expectedTotal = 83.87;
        unusualSpendCategories.put(Category.Travel, expectedTotal);
        testSubject = new EmailProcessor(unusualSpendCategories);
        
        assertEquals("Unusual spending of $83 detected!", testSubject.getEmailHeader());
    }
    
    @Test
    public void check_email_header_amount_when_multiple_categories_exist(){
        unusualSpendCategories.put(Category.Dining, 10.00);
        unusualSpendCategories.put(Category.Travel, 560.00);
        testSubject = new EmailProcessor(unusualSpendCategories);
        assertEquals("Unusual spending of $570 detected!", testSubject.getEmailHeader());
    }
    
    //Email Body Text Tests
    @Test
    public void check_email_body_when_no_high_spending_occurred(){
        String expectedEmailBody = "We have detected no high spending on your card";
        testSubject = new EmailProcessor(unusualSpendCategories);
        assertEquals(expectedEmailBody, testSubject.getEmailCoreReport());
    }
    
    @Test
    public void check_email_body_when_single_high_spending_occurred(){
        String expectedEmailBody = "* You spent $640 on entertainment";
        unusualSpendCategories.put(Category.Entertainment, 640.00);
        String resultEmailCore = new EmailProcessor(unusualSpendCategories).getEmailCoreReport();
        assertTrue(resultEmailCore.contains(expectedEmailBody) , "Actual Result core was: "+resultEmailCore);
    }
    
     @Test
    public void check_email_body_with_three_high_spending_occurrences(){
        unusualSpendCategories.put(Category.Travel, 40.00);
        unusualSpendCategories.put(Category.Dining, 10.00);
        unusualSpendCategories.put(Category.Entertainment, 20.00);
        String resultEmailCore = new EmailProcessor(unusualSpendCategories).getEmailCoreReport();
        assertTrue(resultEmailCore.contains("* You spent $40 on travel") &&
                resultEmailCore.contains("* You spent $10 on dining") &&
                resultEmailCore.contains("* You spent $20 on entertainment"), "Actual Result core was: "+resultEmailCore);
    }
       
    @Test
    public void check_that_when_dining_category_spend_is_zero_then_not_included_in_spend_list(){
        unusualSpendCategories.put(Category.Travel, 40.00);
        unusualSpendCategories.put(Category.Dining, 0.00);
        unusualSpendCategories.put(Category.Entertainment, 20.00);
        String resultEmailCore = new EmailProcessor(unusualSpendCategories).getEmailCoreReport();
        assertTrue(!resultEmailCore.contains("* You spent $0 on dining"));
    }
    
    @Test
    public void check_that_when_dining_category_is_only_category_core_report_no_unusual_spending(){
        unusualSpendCategories.put(Category.Dining, 0.00);
        String resultEmailCore = new EmailProcessor(unusualSpendCategories).getEmailCoreReport();
         assertTrue(resultEmailCore.contains("We have detected no high spending on your card"));
    }

}
