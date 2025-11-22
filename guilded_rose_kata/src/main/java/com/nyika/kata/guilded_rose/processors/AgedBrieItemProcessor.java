package com.nyika.kata.guilded_rose.processors;

import com.nyika.kata.guilded_rose.GildedRose;
import com.nyika.kata.guilded_rose.Item;
import com.nyika.kata.guilded_rose.ItemUtilityHelper;

public class AgedBrieItemProcessor implements IItemProcessor{

     private final int DEFAULT_SELLIN_REDUX_AMT  = 1;

    @Override
    public void processItem(Item item) {  
         if(item.quality != GildedRose.DEFAULT_ROSE_MAX_QUALITY){
             item.quality++;
         }   
         ItemUtilityHelper.reduceSellinByAmt(item, DEFAULT_SELLIN_REDUX_AMT);
    }

}
