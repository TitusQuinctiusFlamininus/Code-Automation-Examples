package com.nyika.kata.guilded_rose;

import com.nyika.kata.guilded_rose.processors.AgedBrieItemProcessor;
import com.nyika.kata.guilded_rose.processors.BackstagePassItemProcessor;
import com.nyika.kata.guilded_rose.processors.BackstageTAFAL80ETCItemProcessor;
import com.nyika.kata.guilded_rose.processors.ConjuredItemProcessor;
import com.nyika.kata.guilded_rose.processors.IItemProcessor;
import com.nyika.kata.guilded_rose.ItemUtilityHelper;
import com.nyika.kata.guilded_rose.processors.NormalItemProcessor;

public class GildedRose {

    public static final int DEFAULT_ROSE_MAX_QUALITY = 50;
    private final int DEFAULT_SELLIN_REDUX_AMT  = 1;

    public static Item[] items;
    private IItemProcessor normalIItemProcessor;
    private IItemProcessor agedBrieItemProcessor;
    private IItemProcessor backStageItemProcessor;
    private IItemProcessor backStageTAFItemProcessor;
    private IItemProcessor conjuredItemProcessor;


    public GildedRose(Item[] theItems) {
        items = theItems;
        initializeProviders();
    }

    private void initializeProviders(){
         normalIItemProcessor = new NormalItemProcessor();   
         agedBrieItemProcessor = new AgedBrieItemProcessor();   
         conjuredItemProcessor = new ConjuredItemProcessor();  
         backStageItemProcessor = new BackstagePassItemProcessor(); 
         backStageTAFItemProcessor = new BackstageTAFAL80ETCItemProcessor(); 
    }

    //------------------------------------------------------------ 
    //NEW IMPLEMENTATION
     //------------------------------------------------------------  
     public void updateNewQuality() {
        for (Item item : items){
            if (item.quality <= DEFAULT_ROSE_MAX_QUALITY)
                {
                switch(item.name)
                {
                    case "Aged Brie":
                        agedBrieItemProcessor.processItem(item);
                        break;
                    case "Backstage passes":
                        backStageItemProcessor.processItem(item);
                        break;
                    case "Backstage passes to a TAFKAL80ETC concert":
                       backStageTAFItemProcessor.processItem(item);
                        break;
                    case "Conjured":
                        conjuredItemProcessor.processItem(item); 
                        break;
                    default:
                        normalIItemProcessor.processItem(item);
                        break;
                }
                 ItemUtilityHelper.reduceSellinByAmt(item, DEFAULT_SELLIN_REDUX_AMT);
            }
        }
     }

    //------------------------------------------------------------ 
    //OLD IMPLEMENTATION
     //------------------------------------------------------------  
    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (!items[i].name.equals("Aged Brie")
                    && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (items[i].quality > 0) {
                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                        items[i].quality = items[i].quality - 1;
                    }
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;

                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }

            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Aged Brie")) {
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].quality > 0) {
                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                                items[i].quality = items[i].quality - 1;
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
    }
}
