
import java.util.HashMap;

/*

System Requirements: https://kata-log.rocks/unusual-spending-kata
 */

class EmailProcessor {
    
    protected final HashMap<Category, Double> summarySpend;
    private final PaymentsProcessor payProcessor;

    EmailProcessor(HashMap<Category, Double> spendCategories) {
        this.summarySpend = spendCategories;
        this.payProcessor = new PaymentsProcessor();
    }

    public String getEmailHeader() {
        
        if(summarySpend.isEmpty() || this.payProcessor.getTotalPaymentsForAllCategories(this.summarySpend)==0){
            return "No Unusual spending detected!";
        }
        else{
            return String.format("Unusual spending of $%d detected!",
                    this.payProcessor.getTotalPaymentsForAllCategories(this.summarySpend));
        }
        
    }
    
    //This is just the core of the report, not the entire report top-to-bottom
    String getEmailCoreReport() {
        String coreReport = "";
        if(this.summarySpend.isEmpty()){    
            return  "We have detected no high spending on your card";
        }
        if(this.summarySpend.size() == 1 && this.summarySpend.containsValue((double)0.00)){
             return  "We have detected no high spending on your card";
        }
        else{
            for(Category category : this.summarySpend.keySet()){
                if(this.summarySpend.get(category) > 0){
                    coreReport += String.format("* You spent $%d on %s\n", this.summarySpend.get(category).intValue(), category.toString().toLowerCase());
                }
            }
            return coreReport;
        }
    }
   
        
    public String getEmailGreeting(){
        return "Hello card user!";
    }
    
    public String getEmailEnding(){
        return "Love,\nThe Credit Card Company";
    }

}
