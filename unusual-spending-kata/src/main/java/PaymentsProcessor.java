
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


class PaymentsProcessor {

    
    private HashMap<String, ArrayList<Payment>> userPayments;

    public ArrayList<Payment> getPaymentsForTargetUserAndMonth(String targetUserId, Month targetMonth) {
        ArrayList<Payment> filtered = new ArrayList();
        for (Payment p : getUserPayments().get(targetUserId)) {
            if( ((p.payMonth.compareTo(targetMonth)) >= -1) && ((p.payMonth.compareTo(targetMonth)) < 1))  {
                filtered.add(p);
            }
        }
        return filtered;
    }
   
    public double getTotalPaymentsForTargetCategoryAndMonth(ArrayList<Payment> monthPayments, Category targetCategory, Month targetMonth) {
        double sum = 0.0;
        for (Payment p : monthPayments.stream().filter(p -> (p.category.equals(targetCategory) && p.payMonth.equals(targetMonth)) ).toArray(Payment[]::new)){
            sum += p.price;  
        }
        return sum;
    }

    public HashMap<Category, Double> getCategoriesAtLeast50(ArrayList<Payment> payments, Month targetMonth) {
        HashMap<Category, Double> catlist = new HashMap();
        for (Category category : Arrays.asList(Category.values())){
            double curr_month_amt = getTotalPaymentsForTargetCategoryAndMonth(payments, category, targetMonth);
            double prev_month_amt = getTotalPaymentsForTargetCategoryAndMonth(payments, category, Month.values()[targetMonth.ordinal()-1]);
            if ((prev_month_amt/curr_month_amt) >= 0.5){
                catlist.put(category, curr_month_amt);
            }
        }
        return catlist;
    }
    
    int getTotalPaymentsForAllCategories(HashMap<Category, Double> summaryPayments) {
        return  summaryPayments.values().stream().reduce(0.0, Double::sum).intValue();
    }
            
    public HashMap<String, ArrayList<Payment>> getUserPayments() {
        return userPayments;
    }

    public void setUserPayments(HashMap<String, ArrayList<Payment>> userPayments) {
        this.userPayments = userPayments;
    }


}
