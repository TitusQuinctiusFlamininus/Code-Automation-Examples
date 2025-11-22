package com.nyika.kata.guilded_rose.processors;

import com.nyika.kata.guilded_rose.Item;

public class ConjuredItemProcessor implements IItemProcessor{

    private static final int NORMAL_DEGRADATION_VALUE = 2;
    private static final int ELEVATED_DEGRADATION_VALUE = 4;

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
    }
    
}
