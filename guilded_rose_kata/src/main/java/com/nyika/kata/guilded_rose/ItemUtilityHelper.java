package com.nyika.kata.guilded_rose;

public class ItemUtilityHelper {

     public static void reduceSellinByAmt(Item item, int amt){
         item.sellIn = item.sellIn - amt;
     }

}
