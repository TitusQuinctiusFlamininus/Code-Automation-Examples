package com.nyika.kata.guilded_rose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class GildedRoseTest {

    Item[] items; 
    GildedRose testSubject;

    @Test
    void foo(){
        Item ordinary_item = new Item("foo", 0, 0);
        items = new Item[] { ordinary_item };
        testSubject = new GildedRose(items);
        
        callTestSubject(1);
        ordinary_item = GildedRose.items[0]; 
        assertEquals("foo", ordinary_item.name);
    }

    // --------------------------------------------------------------------------
    // The test above came with the initial code. Tests below were written tby me to complete the task:

    //NORMAL ITEM TESTS
    @Test
    void check_that_normal_item_quality_degrades_twice_as_fast_after_sellin_date_passes() {
       
        Item ordinary_item = new Item("", 2, 50);
        items = new Item[] { ordinary_item};
        testSubject = new GildedRose(items);

        callTestSubject(3);
        ordinary_item = GildedRose.items[0]; 
        assertEquals(46, ordinary_item.quality);
    }

     @Test
    void check_that_2_normal_items_quality_degrades_twice_as_fast_after_sellin_date_passes() 
    {
       
        Item item1 = new Item("", 2, 50);
        Item item2 = new Item("", 2, 25);
        items = new Item[] { item1, item2};
        testSubject = new GildedRose(items);

        callTestSubject(3);
        Item[] items = GildedRose.items; 
        assertTrue((items[0].quality == 46) && (items[1].quality == 21));

    }

    @Test
    void check_that_normal_item_quality_2_days_after_sellin_date_passes() {
        Item ordinary_item = new Item("", 2, 50);
        items = new Item[] { ordinary_item};
        testSubject = new GildedRose(items);

        callTestSubject(4);
        ordinary_item = GildedRose.items[0]; 
        assertEquals(44, ordinary_item.quality);
    }

    @Test
    void check_when_update_1_day_beyond_sellin_then_quality_is_not_negative() {
        Item ordinary_item = new Item("", 2, 2);
        items = new Item[] { ordinary_item};
        testSubject = new GildedRose(items);
        
        callTestSubject(3);
        ordinary_item = GildedRose.items[0]; 
        assertEquals(0, ordinary_item.quality);
    }

    // AGED BRIE TESTS
    @Test
    public void check_aged_brie_quality_increased_after_1_day() {
        Item aged_brie_item = new Item("Aged Brie", 2, 36);
        items = new Item[] { aged_brie_item};
        testSubject = new GildedRose(items);
        
        callTestSubject(1);
        Item agedBrieItem = GildedRose.items[0];
        assertEquals(37, agedBrieItem.quality);
    }

    @Test
    public void check_aged_brie_quality_49_not_over_50_after_2_days() {
        Item aged_brie_item = new Item("Aged Brie", 2, 49);
        items = new Item[] { aged_brie_item};
        testSubject = new GildedRose(items);
        callTestSubject(2);
        Item agedBrieItem = GildedRose.items[0];
        assertEquals(50, agedBrieItem.quality);
    }

    @Test
    public void check_normal_quality_greater_than_50_not_processed_by_program() {
        int initialQualityValue = 51;
        int initialSellinDaysValue = 3;
        Item item = new Item("", initialSellinDaysValue, initialQualityValue);
        items = new Item[] { item};
        testSubject = new GildedRose(items);
        callTestSubject(5);
        Item normalItem = GildedRose.items[0];
        assertTrue((normalItem.quality == initialQualityValue) && (normalItem.sellIn == initialSellinDaysValue));
    }

    @Test
    public void check_aged_brie_quality_greater_than_50_not_processed_by_program(){
        int initialQualityValue = 51;
        int initialSellinDaysValue = 20;
        Item item = new Item("Aged Brie", initialSellinDaysValue, initialQualityValue);
        items = new Item[] { item};
        testSubject = new GildedRose(items);
        callTestSubject(26);
        Item brieItem = GildedRose.items[0];
        assertTrue((brieItem.quality == initialQualityValue) && (brieItem.sellIn == initialSellinDaysValue));
    }



    //SULFURAS TESTS
     @Test
    public void check_suluras_being_legendary_means_quality_never_alters() {
        Item item = new Item("Sulfuras", 30, 80);
        items = new Item[] { item};
        testSubject = new GildedRose(items);
        callTestSubject(7);
        Item sulfItem = GildedRose.items[0];
        assertEquals(80, sulfItem.quality);
    }

     @Test
    public void check_suluras_being_legendary_means_sellin_never_alters() {
        Item item = new Item("Sulfuras", 8, 80);
        items = new Item[] { item};
        testSubject = new GildedRose(items);
        callTestSubject(7);
        Item sulfItem = GildedRose.items[0];
        assertEquals(8, sulfItem.sellIn);

    }

    //BACKSTAGE PASSES TESTS
     @Test
    public void check_backstage_pass_quality_after_1_sellin_day_and_quality_below_max_quality() {
        Item item = new Item("Backstage passes", 12, 20);
        items = new Item[] { item};
        testSubject = new GildedRose(items);
        callTestSubject(1);
        Item backstageItem = GildedRose.items[0];
        assertEquals(21, backstageItem.quality);
    }

     @Test
    public void check_backstage_pass_when_sellin_becomes_10_after_1_sellin_day_and_quality_below_max_quality() {
        Item item = new Item("Backstage passes", 11, 45);
        items = new Item[] { item};
        testSubject = new GildedRose(items);
        callTestSubject(1);
        Item backstageItem = GildedRose.items[0];
        assertEquals(46, backstageItem.quality);
    }

     @Test
    public void check_backstage_pass_quality_when_sellin_at_10_and_quality_30_and_1_day_passes() {
        Item item = new Item("Backstage passes", 10, 30);
        items = new Item[] { item};
        testSubject = new GildedRose(items);
        callTestSubject(1);
        Item backstageItem = GildedRose.items[0];
        assertEquals(32, backstageItem.quality);
    }

      @Test
    public void check_backstage_pass_quality_when_sellin_at_10_and_quality_30_and_2_days_pass() {
        Item item = new Item("Backstage passes", 10, 30);
        items = new Item[] { item};
        testSubject = new GildedRose(items);
        callTestSubject(2);
        Item backstageItem = GildedRose.items[0];
        assertEquals(34, backstageItem.quality);
    }

     @Test
    public void check_backstage_pass_quality_when_sellin_at_11_and_quality_49_and_1_day_passes() {
        Item item = new Item("Backstage passes", 11, 49);
        items = new Item[] { item};
        testSubject = new GildedRose(items);
        callTestSubject(1);
        Item backstageItem = GildedRose.items[0];
        assertEquals(50, backstageItem.quality);
    }

    @Test
    public void check_backstage_pass_quality_when_sellin_at_10_and_quality_49_and_1_day_passes() {
        Item item = new Item("Backstage passes", 10, 49);
        items = new Item[] { item};
        testSubject = new GildedRose(items);
        callTestSubject(1);
        Item backstageItem = GildedRose.items[0];
        assertEquals(50, backstageItem.quality);
    }

    @Test
    public void check_backstage_pass_quality_when_sellin_at_6_and_quality_30_and_1_day_passes() {
        Item item = new Item("Backstage passes", 6, 30);
        items = new Item[] { item};
        testSubject = new GildedRose(items);
        callTestSubject(1);
        Item backstageItem = GildedRose.items[0];
        assertEquals(32, backstageItem.quality);
    }

    @Test
    public void check_backstage_pass_quality_when_sellin_at_5_and_quality_30_and_1_day_passes() {
        Item item = new Item("Backstage passes", 5, 30);
        items = new Item[] { item};
        testSubject = new GildedRose(items);
        callTestSubject(1);
        Item backstageItem = GildedRose.items[0];
         assertEquals(33, backstageItem.quality);
    }

    @Test
    public void check_backstage_pass_quality_when_sellin_at_5_and_quality_47_and_1_day_passes() {
        Item item = new Item("Backstage passes", 5, 47);
        items = new Item[] { item};
        testSubject = new GildedRose(items);
        callTestSubject(1);
        Item backstageItem = GildedRose.items[0];
         assertEquals(50, backstageItem.quality);
    }

     @Test
    public void check_backstage_pass_quality_when_sellin_at_5_and_quality_48_and_1_day_passes() {
        Item item = new Item("Backstage passes", 5, 47);
        items = new Item[] { item};
        testSubject = new GildedRose(items);
        callTestSubject(1);
        Item backstageItem = GildedRose.items[0];
         assertEquals(50, backstageItem.quality);
    }

     @Test
    public void check_backstage_pass_quality_when_sellin_at_5_and_quality_49_and_1_day_passes() {
        Item item = new Item("Backstage passes", 5, 49);
        items = new Item[] { item};
        testSubject = new GildedRose(items);
        callTestSubject(1);
        Item backstageItem = GildedRose.items[0];
         assertEquals(50, backstageItem.quality);
    }

    @Test
    public void check_backstage_pass_quality_when_sellin_at_3_and_quality_49_and_1_day_passes() {
        Item item = new Item("Backstage passes", 3, 49);
        items = new Item[] { item};
        testSubject = new GildedRose(items);
        callTestSubject(1);
        Item backstageItem = GildedRose.items[0];
         assertEquals(50, backstageItem.quality);
    }

    @Test
    public void check_backstage_pass_quality_when_sellin_at_1_and_quality_30_and_1_day_passes() {
        Item item = new Item("Backstage passes", 1, 30);
        items = new Item[] { item};
        testSubject = new GildedRose(items);
        callTestSubject(1);
        Item backstageItem = GildedRose.items[0];
         assertEquals(33, backstageItem.quality);
    }

    @Test
    public void check_backstage_pass_quality_when_sellin_at_0_and_quality_30_and_1_day_passes() {
        Item item = new Item("Backstage passes", 0, 30);
        items = new Item[] { item};
        testSubject = new GildedRose(items);
        callTestSubject(1);
        Item backstageItem = GildedRose.items[0];
         assertEquals(0, backstageItem.quality);
    }

    @Test
    public void check_backstage_pass_quality_when_sellin_10_days_expired_and_and_1_day_passes() {
        Item item = new Item("Backstage passes", -10, 0);
        items = new Item[] { item};
        testSubject = new GildedRose(items);
        callTestSubject(1);
        Item backstageItem = GildedRose.items[0];
        assertEquals(0, backstageItem.quality);
    }


    //CONJURED ITEMS
     @Test
    public void check_conjured_item_quality_when_sellin_5_and_1_day_passes() {
        Item item = new Item("Conjured", 5, 29);
        items = new Item[] { item};
        testSubject = new GildedRose(items);
        callTestSubject(1);
        Item conjuredItem = GildedRose.items[0];
        assertEquals(27, conjuredItem.quality);
    }

    @Test
    public void check_conjured_item_quality_when_sellin_1_and_1_day_passes() {
        Item item = new Item("Conjured", 1, 43);
        items = new Item[] { item};
        testSubject = new GildedRose(items);
        callTestSubject(1);
        Item conjuredItem = GildedRose.items[0];
        assertEquals(41, conjuredItem.quality);
    }

     @Test
    public void check_conjured_item_quality_when_sellin_0_and_1_day_passes() {
        Item item = new Item("Conjured", 0, 43);
        items = new Item[] { item};
        testSubject = new GildedRose(items);
        callTestSubject(1);
        Item conjuredItem = GildedRose.items[0];
         assertEquals(39, conjuredItem.quality);
    }

     @Test
    public void check_conjured_item_quality_when_sellin_1_and_quality_1_and_1_day_passes() {
         Item item = new Item("Conjured", 1, 1);
        items = new Item[] { item};
        testSubject = new GildedRose(items);
        callTestSubject(1);
        Item conjuredItem = GildedRose.items[0];
         assertEquals(0, conjuredItem.quality);
    }

     @Test
    public void check_conjured_item_quality_when_sellin_0_and_quality_1_and_1_day_passes() {
         Item item = new Item("Conjured", 0, 1);
        items = new Item[] { item};
        testSubject = new GildedRose(items);
        callTestSubject(1);
        Item conjuredItem = GildedRose.items[0];
         assertEquals(0, conjuredItem.quality);
    }

     @Test
    public void check_conjured_item_quality_when_sellin_0_and_quality_1_and_6_days_passes() {
         Item item = new Item("Conjured", 0, 1);
        items = new Item[] { item};
        testSubject = new GildedRose(items);
        callTestSubject(6);
        Item conjuredItem = GildedRose.items[0];
         assertEquals(0, conjuredItem.quality);
    }

     @Test
    public void check_conjured_item_quality_when_sellin_9_and_quality_60_and_1_day_passes() {
         Item item = new Item("Conjured", 9, 60);
        items = new Item[] { item};
        testSubject = new GildedRose(items);
        callTestSubject(1);
        Item conjuredItem = GildedRose.items[0];
         assertEquals(60, conjuredItem.quality);
    }

    //CASES FOR Backstage passes to a TAFKAL80ETC concert
     @Test
    public void check_special_backstage_case_sellin_15_quality_20_and_1_day_passes() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20);
        items = new Item[] { item};
        testSubject = new GildedRose(items);
        callTestSubject(1);
        Item backstageItem = GildedRose.items[0];
         assertEquals(21, backstageItem.quality);
    }

     @Test
    public void check_special_backstage_case_sellin_10_quality_49_and_1_day_passes() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49);
        items = new Item[] { item};
        testSubject = new GildedRose(items);
        callTestSubject(1);
        Item backstageItem = GildedRose.items[0];
         assertEquals(50, backstageItem.quality);
    }

     @Test
    public void check_special_backstage_case_sellin_5_quality_49_and_1_day_passes() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49);
        items = new Item[] { item};
        testSubject = new GildedRose(items);
        callTestSubject(1);
        Item backstageItem = GildedRose.items[0];
         assertEquals(50, backstageItem.quality);
    }
    
    //---------------------------------------------------------------------------
    private GildedRose callTestSubject(int numberOfTimes){
        for (int i = 0; i < numberOfTimes; i++) {
            testSubject.updateNewQuality();
        }
        return testSubject;
    }

}

