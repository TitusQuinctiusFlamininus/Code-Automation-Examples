/*

System Requirements: https://kata-log.rocks/unusual-spending-kata
 */


import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class UnusualSpendingPaymentTests {
    private PaymentsProcessor testSubject;
    private HashMap<String, ArrayList<Payment>> userPayments;
    private ArrayList<Payment> payments;
    
    @BeforeEach 
    public void setUp() throws Exception {
        testSubject = new PaymentsProcessor();
        userPayments = new HashMap();
        payments = new ArrayList();
    }

    @Test
    public void check_if_targetMonth_april_then_no_payments_found(){
        Month expectedMonth = Month.April;
        userPayments.put("myUserId", payments);
        testSubject.setUserPayments(userPayments);
        
        assertTrue(testSubject.getPaymentsForTargetUserAndMonth("myUserId", expectedMonth).isEmpty());
    }
    
    @Test
    public void check_if_targetMonth_april_then_1_payments_found_for_april(){
        Month targetMonth = Month.April;
        double expectedPaymentPrice = 45.57;
        
        Payment p = new Payment();
        p.payMonth = targetMonth;
        p.price = expectedPaymentPrice;
        payments.add(p);
        
        userPayments.put("myUserId", payments);
        testSubject.setUserPayments(userPayments);
         
        Payment firstPayment = testSubject.getPaymentsForTargetUserAndMonth("myUserId", targetMonth).get(0);
        assertTrue((expectedPaymentPrice == firstPayment.price) && (targetMonth.equals(firstPayment.payMonth)));
    }
    
    @Test
    public void check_if_targetMonth_april_then_1_payments_found_for_march(){
        Month expectedMonth = Month.March;
        double expectedPaymentPrice = 34.56;
        
        Payment p = new Payment();
        p.payMonth = expectedMonth;
        p.price = expectedPaymentPrice;
        
        payments.add(p);
        
        userPayments.put("myUserId", payments);
        testSubject.setUserPayments(userPayments);
         
        Payment firstPayment = testSubject.getPaymentsForTargetUserAndMonth("myUserId", expectedMonth).get(0);
        assertTrue((expectedPaymentPrice == firstPayment.price) && (expectedMonth.equals(firstPayment.payMonth)));
    }
    
    @Test
    public void check_if_targetMonth_april_then_2_payments_found_for_april_and_march(){
        Month targetMonth = Month.April;
        double expectedMarchPaymentPrice = 8.23;
        double expectedAprilPaymentPrice = 2.30;
        
        Payment marchPayment = new Payment();
        marchPayment.payMonth = Month.March;
        marchPayment.price = expectedMarchPaymentPrice;
        
        Payment aprilPayment = new Payment();
        aprilPayment.payMonth = targetMonth;
        aprilPayment.price = expectedAprilPaymentPrice;
        payments.add(aprilPayment);
        payments.add(marchPayment);
     
        
        userPayments.put("myUserId", payments);
        testSubject.setUserPayments(userPayments);
         
        ArrayList<Payment> allPayments = testSubject.getPaymentsForTargetUserAndMonth("myUserId", targetMonth);

        assertTrue(
                (expectedMarchPaymentPrice == allPayments.get(1).price) &&
                (expectedAprilPaymentPrice == allPayments.get(0).price) &&
                        2 == allPayments.size());
    }
    
    @Test
    public void check_if_targetMonth_april_and_february_exists_then_no_payment_is_from_februrary(){
        Month targetMonth = Month.April;
        String targetUserId = "myUserId";
         
        Payment febPayment = new Payment();
        febPayment.payMonth = Month.February;
        
        Payment marchPayment = new Payment();
        marchPayment.payMonth = Month.March;
        
        Payment aprilPayment = new Payment();
        aprilPayment.payMonth = targetMonth;
        
        payments.add(febPayment);
        payments.add(marchPayment);
        payments.add(aprilPayment);
  
        userPayments.put(targetUserId, payments);
        testSubject.setUserPayments(userPayments);
         
        ArrayList<Payment> allPayments = testSubject.getPaymentsForTargetUserAndMonth(targetUserId, targetMonth);

        assertTrue(
                (Month.February != allPayments.get(0).payMonth) && 
                (Month.February != allPayments.get(1).payMonth) &&
                        2 == allPayments.size());
    }
    
    @Test
    public void check_if_targetMonth_may_and_march_exists_then_no_payment_is_from_march(){
        Month targetMonth = Month.May;
        String targetUserId = "myUserId";
        
        Payment marchPayment = new Payment();
        marchPayment.payMonth = Month.March;
        
        Payment aprilPayment = new Payment();
        aprilPayment.payMonth = Month.April;
        
        Payment mayPayment = new Payment();
        mayPayment.payMonth = targetMonth;
        
        payments.add(marchPayment);
        payments.add(aprilPayment);
        payments.add(mayPayment);
  
        userPayments.put(targetUserId, payments);
        testSubject.setUserPayments(userPayments);
         
        ArrayList<Payment> allPayments = testSubject.getPaymentsForTargetUserAndMonth(targetUserId, targetMonth);

        assertTrue(
                (Month.March != allPayments.get(0).payMonth) && 
                (Month.March != allPayments.get(1).payMonth) &&
                        2 == allPayments.size());
    }
    
 
    @Test
    public void check_if_targetMonth_april_and_2_payments_for_different_userIds_then_only_targetedUser_returned(){
        Month targetMonth = Month.April;
        String targetUserId = "completelyDifferentUserId";
  
        payments.add(new Payment());
        
        userPayments.put("myUserId", payments);
        userPayments.put(targetUserId, new ArrayList() );
        testSubject.setUserPayments(userPayments);
         
        assertEquals(0, testSubject.getPaymentsForTargetUserAndMonth(targetUserId, targetMonth).size());
    }
    
    @Test
    public void check_if_targetMonth_is_may_then_june_payment_does_not_appear(){
         Month targetMonth = Month.May;
        String targetUserId = "myUserId";
        
        Payment marchPayment = new Payment();
        marchPayment.payMonth = Month.March;
        
        Payment aprilPayment = new Payment();
        aprilPayment.payMonth = Month.April;
        
        Payment mayPayment = new Payment();
        mayPayment.payMonth = targetMonth;
        
        Payment junePayment = new Payment();
        junePayment.payMonth = Month.June;
        
        payments.add(marchPayment);
        payments.add(aprilPayment);
        payments.add(mayPayment);
        payments.add(junePayment);
  
        userPayments.put(targetUserId, payments);
        testSubject.setUserPayments(userPayments); 
        
        ArrayList<Payment> allPayments = testSubject.getPaymentsForTargetUserAndMonth(targetUserId, targetMonth);
        assertTrue(
                (Month.June != allPayments.get(0).payMonth) && 
                (Month.June != allPayments.get(1).payMonth) &&
                2 == allPayments.size());
    }
    
    //we need this test to ensure that we do not bring back
    //payments from the wrong relative month, given a target month, since ordinal
    //order could be changed without a corresponding system behavioural change
    @Test
    public void check_order_of_months_as_expected(){
        assertTrue(0 == Month.January.ordinal() &&
                1 == Month.February.ordinal() &&
                2 == Month.March.ordinal() &&
                3 == Month.April.ordinal() &&
                4 == Month.May.ordinal() &&
                5 == Month.June.ordinal() &&
                6 == Month.July.ordinal() &&
                7 == Month.August.ordinal() &&
                8 == Month.September.ordinal() &&
                9 == Month.October.ordinal() &&
                10 == Month.November.ordinal() &&
                11 == Month.December.ordinal());
    }
    
    
     @Test
    public void check_if_single_payment_for_dining_category_matches_expectations(){
        double expectedTotalPayment = 45.99;
        Month targetMonth = Month.January;
        Category targetCategory = Category.Dining;
        
        Payment somePayment = new Payment();
        somePayment.price = expectedTotalPayment;
        somePayment.payMonth = targetMonth;
        somePayment.category = targetCategory;
        
        payments.add(somePayment);
        assertEquals(expectedTotalPayment, testSubject.getTotalPaymentsForTargetCategoryAndMonth(payments, targetCategory, targetMonth));
    }
    
    @Test
    public void check_if_2_categories_present_only_1_considered_for_total_payments(){
        double expectedTotalPayment = 950.45;
        Month targetMonth = Month.January;
        Category targetCategory = Category.Dining;
        
        Payment diningPayment = new Payment();
        diningPayment.price = expectedTotalPayment;
        diningPayment.payMonth = targetMonth;
        diningPayment.category = targetCategory;
        
        Payment travelPayment = new Payment();
        travelPayment.price = 450.00;
        travelPayment.category = Category.Travel;
        
        payments.add(travelPayment);
        payments.add(diningPayment);
        
        
        assertEquals(expectedTotalPayment, testSubject.getTotalPaymentsForTargetCategoryAndMonth(payments, targetCategory, targetMonth));
    }
    
    @Test
    public void check_only_4_from_6_payments_from_january_are_aggregated_as_expected(){
        double expectedTotalPayment = 60.00;
        Month targetMonth = Month.January;
        Category targetCategory = Category.Entertainment;
        
        Payment diningPayment = new Payment();
        diningPayment.price = 56.88;
        diningPayment.category = Category.Dining;
        
        Payment ent1 = new Payment();
        ent1.price = 15.00;
        ent1.payMonth = targetMonth;
        ent1.category = targetCategory;
        
        Payment ent2 = new Payment();
        ent2.price = 30.00;
        ent2.payMonth = targetMonth;
        ent2.category = targetCategory;
        
        Payment travelPayment = new Payment();
        travelPayment.price = expectedTotalPayment;
        travelPayment.category = Category.Travel;
        
        Payment ent3 = new Payment();
        ent3.price = 10.00;
        ent3.payMonth = targetMonth;
        ent3.category = targetCategory;
        
        Payment ent4 = new Payment();
        ent4.price = 5.00;
        ent4.payMonth = targetMonth;
        ent4.category = targetCategory;
        
        payments.add(ent4);
        payments.add(ent3);
        payments.add(travelPayment);
        payments.add(ent2);
        payments.add(diningPayment);
        payments.add(ent1);
        
        assertEquals(expectedTotalPayment, testSubject.getTotalPaymentsForTargetCategoryAndMonth(payments, targetCategory, targetMonth));
    }

    @Test
    public void check_only_2_from_6_payments_from_january_are_aggregated_as_expected(){
        double expectedTotalPayment = 1000.00;
        Month targetMonth = Month.January;
        Category targetCategory = Category.Travel;
        
        
        Payment entertain1 = new Payment();
        entertain1.category = Category.Entertainment;
        
        Payment dining1 = new Payment();
        dining1.category = Category.Dining;
        
        Payment travelPayment1 = new Payment();
        travelPayment1.price = 450.00;
        travelPayment1.payMonth = targetMonth;
        travelPayment1.category = targetCategory;
        
        Payment dining2 = new Payment();
        dining2.category = Category.Dining;       
        
        Payment entertain2 = new Payment();
        entertain2.category = Category.Entertainment;
        
        Payment travelPayment2 = new Payment();
        travelPayment2.price = 550.00;
        travelPayment2.payMonth = targetMonth;
        travelPayment2.category = targetCategory;
        
        payments.add(entertain1);
        payments.add(dining1);
        payments.add(travelPayment1);
        payments.add(dining2);
        payments.add(entertain2);
        payments.add(travelPayment2);
        assertEquals(expectedTotalPayment, testSubject.getTotalPaymentsForTargetCategoryAndMonth(payments, targetCategory, targetMonth));
    }
    
     @Test
    public void check_only_3_from_6_payments_aggregated_when_all_categories_in_same_month(){
        double expectedTotalPayment = 360.00;
        Month targetMonth = Month.August;
        Category targetCategory = Category.Dining;
        
        
        Payment entertain1 = new Payment();
        entertain1.price = 25.00;
        entertain1.payMonth = targetMonth;
        entertain1.category = Category.Entertainment;
        
        Payment dining1 = new Payment();
        dining1.payMonth = targetMonth;
        dining1.price = 250.00;
        dining1.category = targetCategory;
        
        Payment travelPayment1 = new Payment();
        travelPayment1.price = 25.00;
        travelPayment1.payMonth = targetMonth;
        travelPayment1.category = Category.Travel;
        
        Payment dining2 = new Payment();
        dining2.payMonth = targetMonth;
        dining2.price = 40.00;
        dining2.category = targetCategory;       
        
        Payment dining3 = new Payment();
        dining3.payMonth = targetMonth;
        dining3.price = 70.00;
        dining3.category = targetCategory;
        
        Payment travelPayment2 = new Payment();
        travelPayment2.price = 25.00;
        travelPayment2.payMonth = targetMonth;
        travelPayment2.category = Category.Travel;
        
        payments.add(entertain1);
        payments.add(dining1);
        payments.add(travelPayment1);
        payments.add(dining2);
        payments.add(dining3);
        payments.add(travelPayment2);
        
        assertEquals(expectedTotalPayment, testSubject.getTotalPaymentsForTargetCategoryAndMonth(payments, targetCategory, targetMonth));
    }
    
    @Test
    public void check_only_specific_month_considered_for_category_when_calculating_total_payments(){
        double expectedTotalPayment = 50.00;
        Month targetMonth = Month.July;
        Category targetCategory = Category.Dining;
        
        
        Payment dining1 = new Payment();
        dining1.price = 25.00;
        dining1.payMonth = Month.August;
        dining1.category = targetCategory;

        Payment dining2 = new Payment();
        dining2.price = 50.00;
        dining2.payMonth = targetMonth;
        dining2.category = targetCategory;
        
        payments.add(dining1);
        payments.add(dining2);
        
        assertEquals(expectedTotalPayment, testSubject.getTotalPaymentsForTargetCategoryAndMonth(payments, targetCategory, targetMonth));
    }
    
    @Test
    public void check_no_categories_satisy_condition_for_at_least_50_percent_spend(){
        Month targetMonth = Month.September;
        
        Payment dining1 = new Payment();
        dining1.price = 50.00;
        dining1.payMonth = targetMonth;
        dining1.category = Category.Dining;

        Payment dining2 = new Payment();
        dining2.price = 5.00;
        dining2.payMonth = Month.August;
        dining2.category = Category.Dining;
        
        payments.add(dining1);
        payments.add(dining2);
        
        HashMap<Category, Double> fiftyPercentCatList = testSubject.getCategoriesAtLeast50(payments, targetMonth);
        assertTrue(fiftyPercentCatList.isEmpty());
    }
    
     @Test
    public void check_travel_category_matches_at_least_50_percent_spend(){
        Month targetMonth = Month.September;
        double intendedTargetSpendPrice = 50.00;
        
        Payment travel1 = new Payment();
        travel1.price = intendedTargetSpendPrice;
        travel1.payMonth = targetMonth;
        travel1.category = Category.Travel;

        Payment travel2 = new Payment();
        travel2.price = 25.00;
        travel2.payMonth = Month.August;
        travel2.category = Category.Travel;
        
        payments.add(travel1);
        payments.add(travel2);
        
        HashMap<Category, Double> fiftyPercentCatList = testSubject.getCategoriesAtLeast50(payments, targetMonth);
        assertTrue( (fiftyPercentCatList.size() == 1) && 
                fiftyPercentCatList.get(Category.Travel).equals(intendedTargetSpendPrice));
    }
    
     @Test
    public void check_category_slightly_below_50_percent_threshold_does_not_make_category_list(){
        Month targetMonth = Month.September;
        
        Payment travel1 = new Payment();
        travel1.price = 50.00;
        travel1.payMonth = targetMonth;
        travel1.category = Category.Travel;

        Payment travel2 = new Payment();
        travel2.price = 24.00;
        travel2.payMonth = Month.August;
        travel2.category = Category.Travel;
        
        payments.add(travel1);
        payments.add(travel2);
        
        assertTrue(testSubject.getCategoriesAtLeast50(payments, targetMonth).isEmpty());
    }
    
    @Test
    public void check_total_payments_zero_when_no_category_exceeds_previous_month(){
        HashMap<Category, Double> allCategoryPayments = new HashMap();
        allCategoryPayments.put(Category.Travel, 0.0);
        assertEquals(0, testSubject.getTotalPaymentsForAllCategories(allCategoryPayments));
    }
    
    @Test
    public void check_total_payments_matches_when_1_category_exceeds_previous_month(){
         HashMap<Category, Double> allCategoryPayments = new HashMap();
         allCategoryPayments.put(Category.Travel, 60.55);
         assertEquals(60.0, testSubject.getTotalPaymentsForAllCategories(allCategoryPayments));
    }
    
     @Test
    public void check_total_payments_matches_when_3_category_exceeds_previous_month(){
         HashMap<Category, Double> allCategoryPayments = new HashMap();
         allCategoryPayments.put(Category.Dining, 100.0);
         allCategoryPayments.put(Category.Entertainment, 40.0);
         allCategoryPayments.put(Category.Travel, 110.0);
         assertEquals(250.0, testSubject.getTotalPaymentsForAllCategories(allCategoryPayments));
    }

}
