package com.nyika.kata.guilded_rose.processors;

import com.nyika.kata.guilded_rose.GildedRose;
import com.nyika.kata.guilded_rose.Item;

public class BackstagePassItemProcessor implements IItemProcessor{

    //Price value increases
    private static final int PREMIUM_ELEVATED_VALUE    = 2;
    private static final int SUPER_ELEVATED_VALUE      = 3;

    //Sell Bounds
    private static final int SELL_UPPER_BOUND = 10;
    private static final int SELL_LOWER_BOUND = 5;


    @Override
    public void processItem(Item item) {
        if(item.sellIn<=SELL_UPPER_BOUND && item.sellIn > SELL_LOWER_BOUND){
             makePremiumIfApplicable(item, PREMIUM_ELEVATED_VALUE);
        }
       else if(item.sellIn <= SELL_LOWER_BOUND && item.sellIn > 0){
             makePremiumIfApplicable(item, SUPER_ELEVATED_VALUE);
            }
        else if (item.sellIn <=0){
            item.quality = 0;
        }                  
        else{                     
            item.quality++;   
        }     
    }

    private void makePremiumIfApplicable(Item item, int priceIncrease){
        if((item.quality+priceIncrease) <= GildedRose.DEFAULT_ROSE_MAX_QUALITY) {
                item.quality = item.quality+priceIncrease;                            
            }
            else{
                item.quality = GildedRose.DEFAULT_ROSE_MAX_QUALITY;    
            }
                
    }
    
}
