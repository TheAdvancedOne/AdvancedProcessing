package com.theadvancedone.advancedprocessing.misc;

import net.minecraft.item.Item;

import java.util.List;

public class ItemSlot {
    private int index;
    private List<Item> itemList;

    public ItemSlot(int index, List<Item> itemList) {
        this.index = index;
        this.itemList = itemList;
    }


    public List<Item> getItemList() {
        return itemList;
    }

    public int getIndex() {
        return index;
    }
}
