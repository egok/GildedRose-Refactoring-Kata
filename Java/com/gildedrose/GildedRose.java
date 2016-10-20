package com.gildedrose;

class GildedRose {
    Item[] items;

    private static final String AGED_BRIE = "Aged Brie";
    private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    private static final String CONJURED = "Conjured Mana Cake";

    public GildedRose(Item[] items)
    {
        this.items = items;
    }

    public void updateQuality()
    {
        for (int i = 0; i < items.length; i++) {

            try
            {
                updateQualityOfAnItem(i, items[i]);
            }
            catch (IllegalArgumentException ex)
            {
                System.out.println("This item has quality more than 50");
            }
        }
    }

    public int qualityFor(int itemNumber)
    {
        return items[itemNumber].quality;
    }

    public int sellInFor(int itemNumber)
    {
        return items[itemNumber].sellIn;
    }

    public String nameFor(int itemNumber)
    {
        return items[itemNumber].name;
    }

    private void updateQualityOfAnItem(int i, Item item) throws IllegalArgumentException
    {
        String name = item.name;
        int quality = item.quality;
        int sellIn = item.sellIn;
        switch (name)
        {
            case AGED_BRIE:
                throwExceptionIfQualityIsAbove50(quality);
                updateAgedBrieQualityAndSellIn(i, quality);
                break;
            case SULFURAS_HAND_OF_RAGNAROS:
                break;
            case BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT:
                throwExceptionIfQualityIsAbove50(quality);
                updateBackStagePassesQualityAndSellIn(i, sellIn);
                break;
            case CONJURED:
                throwExceptionIfQualityIsAbove50(quality);
                updateConjuredQualityAndSellIn(i, sellIn, quality);
                break;
            default:
                throwExceptionIfQualityIsAbove50(quality);
                updateItemQualityAndSellIn(i, sellIn, quality);
        }
    }

    private void throwExceptionIfQualityIsAbove50(int quality)
    {
        if(quality>50)
        {
            throw new IllegalArgumentException("This item has quality more than 50");
        }
    }

    private void updateAgedBrieQualityAndSellIn(int itemPlace, int quality)
    {
        if(quality < 50)
        {
            items[itemPlace].quality++;
        }
        if(quality > 0)
        {
            items[itemPlace].sellIn--;
        }
    }

    private void updateBackStagePassesQualityAndSellIn(int itemPlace, int sellIn)
    {
        if (sellIn > 10)
        {
            items[itemPlace].quality++;
            items[itemPlace].sellIn--;
        }
        else if (sellIn > 5)
        {
            items[itemPlace].quality = items[itemPlace].quality + 2;
            items[itemPlace].sellIn--;
        }
        else if (sellIn > 0)
        {
            items[itemPlace].quality = items[itemPlace].quality + 3;
            items[itemPlace].sellIn--;
        }
        else
            {
                items[itemPlace].quality = 0;
            }
    }

    private void updateConjuredQualityAndSellIn(int itemPlace, int sellIn, int quality)
    {
        if (sellIn > 0)
        {
            items[itemPlace].quality = items[itemPlace].quality - 2;
            items[itemPlace].sellIn--;
        }
        else if(quality > 3)
        {
            items[itemPlace].quality = items[itemPlace].quality - 4;
        }
        else if(quality > 0)
        {
            items[itemPlace].quality = 0;
        }
    }

    private void updateItemQualityAndSellIn(int itemPlace, int sellIn, int quality)
    {
        if (sellIn > 0)
        {
            items[itemPlace].quality--;
            items[itemPlace].sellIn--;
        }
        else if(quality > 1)
        {
            items[itemPlace].quality = items[itemPlace].quality - 2;
        }
        else if(quality == 1)
        {
            items[itemPlace].quality--;
        }
    }
}
