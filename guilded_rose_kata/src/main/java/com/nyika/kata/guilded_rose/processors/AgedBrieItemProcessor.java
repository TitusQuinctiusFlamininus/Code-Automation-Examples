package com.nyika.kata.guilded_rose.processors;

import com.nyika.kata.guilded_rose.GildedRose;
import com.nyika.kata.guilded_rose.Item;

public class AgedBrieItemProcessor implements IItemProcessor{

    @Override
    public void processItem(Item item) {  
         if(item.quality != GildedRose.DEFAULT_ROSE_MAX_QUALITY){
             item.quality++;
         }   
    }

}
