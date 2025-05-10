
import java.util.ArrayList;
import java.util.HashMap;

/*

System Requirements: https://kata-log.rocks/unusual-spending-kata
 */

public class UnusualSpendingReport {


    public static void main(String[] args) {
       
        System.out.println("=====================");
        System.out.println("  JULY REPORT");
        System.out.println("=====================");
        printFinalReport(Month.July);
        
        System.out.println("=====================");
        System.out.println("  AUGUST REPORT");
        System.out.println("=====================");
        printFinalReport(Month.August);
        
        System.out.println("=====================");
        System.out.println("  SEPTEMBER REPORT");
        System.out.println("=====================");
        printFinalReport(Month.September);
        
        System.out.println("=====================");
        System.out.println("  OCTOBER REPORT");
        System.out.println("=====================");
        printFinalReport(Month.October);
        
        System.out.println("=====================");
        System.out.println("  NOVEMEBER REPORT");
        System.out.println("=====================");
        printFinalReport(Month.November);
        
        
    }

    private static void printFinalReport(Month targetMonth) {
        PaymentsProcessor payProcessor = new PaymentsProcessor();
        payProcessor.setUserPayments(getAllPayments());
        ArrayList<Payment>  paymentsForUserOfInterest = payProcessor.getPaymentsForTargetUserAndMonth("me", targetMonth);
        
        HashMap<Category, Double> catToPaymentsReport = payProcessor.getCategoriesAtLeast50(paymentsForUserOfInterest, targetMonth);
        EmailProcessor emailer = new EmailProcessor(catToPaymentsReport);
        String emailHeader = emailer.getEmailHeader();
        String emailGreeting = emailer.getEmailGreeting();
        String emailCore = emailer.getEmailCoreReport();
        String emailEnding = emailer.getEmailEnding();
        
        String fullEmail = emailHeader+"\n\n"+emailGreeting+"\n\n"+emailCore+"\n\n"+emailEnding+"\n";
        
        System.out.println(fullEmail);
    }
    
   
    private static HashMap<String, ArrayList<Payment>> getAllPayments(){
        HashMap<String, ArrayList<Payment>> userToPaymentsMap = new HashMap();
        ArrayList<Payment> payments = new ArrayList();
        
        Payment travelPayment1 = new Payment();
        travelPayment1.payMonth = Month.November;
        travelPayment1.category = Category.Travel;
        travelPayment1.price = 235.35;
        travelPayment1.description = "Had to go to Vegas";
        
        Payment travelPayment2 = new Payment();
        travelPayment2.payMonth = Month.October;
        travelPayment2.category = Category.Travel;
        travelPayment2.price = 625.50;
        travelPayment2.description = "Had to go to Portugal";
        
        Payment entPayment1 = new Payment();
        entPayment1.payMonth = Month.November;
        entPayment1.category = Category.Entertainment;
        entPayment1.price = 66.99;
        entPayment1.description = "Clients were in town for a merger";
        
        Payment entPayment2 = new Payment();
        entPayment2.payMonth = Month.October;
        entPayment2.category = Category.Entertainment;
        entPayment2.price = 864.00;
        entPayment2.description = "Had to eat, i was hungry";
        
        Payment entPayment3 = new Payment();
        entPayment3.payMonth = Month.December;
        entPayment3.category = Category.Entertainment;
        entPayment3.price = 70.90;
        entPayment3.description = "My sister was in town";
        
        Payment dining1 = new Payment();
        dining1.payMonth = Month.October;
        dining1.category = Category.Dining;
        dining1.price = 700.30;
        dining1.description = "Clients came back, had to please them";
        
        Payment dining2 = new Payment();
        dining2.payMonth = Month.September;
        dining2.category = Category.Dining;
        dining2.price = 5.30;
        dining2.description = "Had to dash at lunch for a quick bite";
        
        Payment travelPayment3 = new Payment();
        travelPayment3.payMonth = Month.September;
        travelPayment3.category = Category.Travel;
        travelPayment3.price = 45.95;
        travelPayment3.description = "Always wanted to see the grand canyon";
    
        Payment entPayment4 = new Payment();
        entPayment4.payMonth = Month.November;
        entPayment4.category = Category.Entertainment;
        entPayment4.price = 8.00;
        entPayment4.description = "The new star wars came out";
        
        Payment dining3 = new Payment();
        dining3.payMonth = Month.October;
        dining3.category = Category.Dining;
        dining3.price = 1500.45;
        dining3.description = "Clients seem to think i'm made of money";   
        
        Payment travelPayment4 = new Payment();
        travelPayment4.payMonth = Month.August;
        travelPayment4.category = Category.Travel;
        travelPayment4.price = 600.00;
        travelPayment4.description = "My friend in iceland was sick";
        
        
        payments.add(travelPayment1);
        payments.add(entPayment1);
        payments.add(travelPayment2);
        payments.add(entPayment3);
        payments.add(dining1);
        payments.add(entPayment2);
        payments.add(dining2);
        payments.add(travelPayment3);
        payments.add(entPayment4);
        payments.add(dining3);
        payments.add(travelPayment4);
        
        userToPaymentsMap.put("me", payments);
        return userToPaymentsMap;
        
    }
    
}
