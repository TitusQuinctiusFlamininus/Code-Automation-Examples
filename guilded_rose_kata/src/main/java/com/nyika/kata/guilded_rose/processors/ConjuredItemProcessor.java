package com.nyika.kata.guilded_rose.processors;

import com.nyika.kata.guilded_rose.Item;
import com.nyika.kata.guilded_rose.ItemUtilityHelper;

public class ConjuredItemProcessor implements IItemProcessor{

    private static final int NORMAL_DEGRADATION_VALUE = 2;
    private static final int ELEVATED_DEGRADATION_VALUE = 4;
    private final int DEFAULT_SELLIN_REDUX_AMT  = 1;

    @Override
    public void processItem(Item item) {
       if(item.sellIn<=0){
            if(item.quality-ELEVATED_DEGRADATION_VALUE < 0){
                item.quality = 0;
            }
            else{
                item.quality= item.quality-ELEVATED_DEGRADATION_VALUE;
            }                   
        }
        else{
            if(item.quality-NORMAL_DEGRADATION_VALUE < 0){
                item.quality = 0;
            }
            else{
                item.quality= item.quality-NORMAL_DEGRADATION_VALUE;
            }
        }
         ItemUtilityHelper.reduceSellinByAmt(item, DEFAULT_SELLIN_REDUX_AMT);
    }
    
}
