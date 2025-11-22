package com.nyika.kata.guilded_rose.processors;

import com.nyika.kata.guilded_rose.Item;


public class NormalItemProcessor implements IItemProcessor {

    private final int DEFAULT_QUALITY_REDUX_AMT = 1;
    private final int PREMIUM_QUALITY_REDUX_AMT = 2;

    @Override
    public void processItem(Item item) {  
        if (item.sellIn <= 0){
                if((item.quality - PREMIUM_QUALITY_REDUX_AMT) < 0){
                item.quality = 0;
            }
        else{
                reduceQualityByAmt(item, PREMIUM_QUALITY_REDUX_AMT);
            }
        }
        else{
                reduceQualityByAmt(item, DEFAULT_QUALITY_REDUX_AMT);
        }
     }

     private void reduceQualityByAmt(Item item, int amt){
         item.quality = item.quality - amt;
     }
       
}