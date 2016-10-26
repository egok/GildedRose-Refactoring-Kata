package com.gildedrose;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;

public class GildedRoseTest {

    private GildedRose myRose;

    @Test
    public void foo()
    {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    public void eachUpdateDecreasesQualityValue()
    {
        myRose = new GildedRoseItemsBuilder()
                .name("foo")
                .quality(10)
                .sellIn(2)
                .update();

        assertEquals(9, myRose.qualityFor(0));
        assertEquals(1, myRose.sellInFor(0));
    }

    @Test
    public void eachUpdateDecreasesSellInValue()
    {
        myRose = new GildedRoseItemsBuilder()
                .name("foo")
                .quality(9)
                .sellIn(1)
                .update();

        assertEquals(0, myRose.sellInFor(0));
    }

    @Test
    public void eachUpdateDecreasesQualityTwiceAsFastWhenExpired()
    {
        myRose = new GildedRoseItemsBuilder()
                .name("foo")
                .quality(8)
                .sellIn(0)
                .update();

        assertEquals(6, myRose.qualityFor(0));
        assertEquals(0, myRose.sellInFor(0));
    }

    @Test
    public void qualityOfAnItemIsNeverNegativeWithQuality1()
    {
        myRose = new GildedRoseItemsBuilder()
                .name("foo")
                .quality(1)
                .sellIn(0)
                .update();

        assertEquals(myRose.qualityFor(0) >= 0, true);
        assertEquals(0, myRose.sellInFor(0));
    }

    @Test
    public void qualityOfAnItemIsNeverNegativeWithQuality2()
    {
        myRose = new GildedRoseItemsBuilder()
                .name("foo")
                .quality(2)
                .sellIn(0)
                .update();

        assertEquals(myRose.qualityFor(0) >= 0, true);
        assertEquals(0, myRose.sellInFor(0));
    }

    @Test
    public void qualityOfAnItemIsNeverNegative()
    {
        myRose = new GildedRoseItemsBuilder()
                .name("foo")
                .quality(0)
                .sellIn(0)
                .update();

        assertEquals(myRose.qualityFor(0) >= 0, true);
        assertEquals(0, myRose.sellInFor(0));
    }

    @Test
    public void eachUpdateIncreasesQualityOfAgedBrie()
    {
        myRose = new GildedRoseItemsBuilder()
                .name("Aged Brie")
                .quality(8)
                .sellIn(9)
                .update();

        assertEquals(9, myRose.qualityFor(0));
        assertEquals(8, myRose.sellInFor(0));
    }



    @Test
    public void qualityOfAnItemIsNeverMoreThan50()
    {
        myRose = new GildedRoseItemsBuilder()
                .name("Aged Brie")
                .quality(50)
                .sellIn(9)
                .update();

        assertEquals(50, myRose.qualityFor(0));
        assertEquals(8, myRose.sellInFor(0));
    }

    @Test
    public void qualityOfSulfurasNeverChanges()
    {
        myRose = new GildedRoseItemsBuilder()
                .name("Sulphurs, Hand of Ragnaros")
                .quality(70)
                .sellIn(0)
                .update();

        assertEquals(70, myRose.qualityFor(0));
        assertEquals(0, myRose.sellInFor(0));
    }

    @Test
    public void sellInOfSulfurasNeverChanges()
    {
        myRose = new GildedRoseItemsBuilder()
                .name("Sulphurs, Hand of Ragnaros")
                .quality(80)
                .sellIn(0)
                .update();

        assertEquals(80, myRose.qualityFor(0));
        assertEquals(0, myRose.sellInFor(0));
    }

    @Test
    public void qualityOfBSPassesIncreasesBy2Before10DaysSellInDate()
    {
        myRose = new GildedRoseItemsBuilder()
                .name("Backstage passes to a TAFKAL80ETC concert")
                .quality(10)
                .sellIn(10)
                .update();

        assertEquals(12, myRose.qualityFor(0));
        assertEquals(9, myRose.sellInFor(0));
    }

    @Test
    public void qualityOfBSPassesIncreasesBy3Before5DaysSellInDate()
    {
        myRose = new GildedRoseItemsBuilder()
                .name("Backstage passes to a TAFKAL80ETC concert")
                .quality(10)
                .sellIn(5)
                .update();

        assertEquals(13, myRose.qualityFor(0));
        assertEquals(4, myRose.sellInFor(0));
    }

    @Test
    public void qualityOfBSPassesDrops0AfterSellInDate()
    {
        myRose = new GildedRoseItemsBuilder()
                .name("Backstage passes to a TAFKAL80ETC concert")
                .quality(10)
                .sellIn(0)
                .update();

        assertEquals(0, myRose.qualityFor(0));
        assertEquals(0, myRose.sellInFor(0));
    }

    @Test
    public void qualityOfConjuredDegradeBy2()
    {
        myRose = new GildedRoseItemsBuilder()
                .name("Conjured Mana Cake")
                .quality(10)
                .sellIn(10)
                .update();

        assertEquals(8, myRose.qualityFor(0));
        assertEquals(9, myRose.sellInFor(0));
    }

    @Test
    public void qualityOfConjuredDegradeBy4WhenSellInIs0()
    {
        myRose = new GildedRoseItemsBuilder()
                .name("Conjured Mana Cake")
                .quality(10)
                .sellIn(0)
                .update();

        assertEquals(6, myRose.qualityFor(0));
        assertEquals(0, myRose.sellInFor(0));
    }

    /***************************************************
            REQUIREMENT SPEC TESTS ENDS
     ***************************************************/

    private class GildedRoseItemsBuilder
    {
        private GildedRose myGildedRose;
        private Item[] myItems;
        private Item myItem;

        GildedRoseItemsBuilder()
        {
            myItem = new Item("", 0 , 0);
        }
        GildedRoseItemsBuilder name(String name)
        {
            myItem.name = name;
            return this;
        }

        GildedRoseItemsBuilder sellIn(int sellIn)
        {
            myItem.sellIn = sellIn;
            return this;
        }

        GildedRoseItemsBuilder quality(int quality)
        {
            myItem.quality = quality;
            return this;
        }

        Item build()
        {
            return myItem;
        }

        GildedRose buildWithItems(Item ...i1)
        {
            myGildedRose = new GildedRose(i1);
            return myGildedRose;
        }

        GildedRose update()
        {
            myItems = new Item[]{myItem};
            myGildedRose = new GildedRose(myItems);
            myGildedRose.updateQuality();
            return myGildedRose;
        }
    }
}
