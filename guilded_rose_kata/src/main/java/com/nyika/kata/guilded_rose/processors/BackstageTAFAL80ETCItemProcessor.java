package com.nyika.kata.guilded_rose.processors;

import com.nyika.kata.guilded_rose.Item;

public class BackstageTAFAL80ETCItemProcessor implements IItemProcessor {

    @Override
    public void processItem(Item item) {
        item.quality++;
        item.sellIn--;
    }

}
