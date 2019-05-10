package com.mobiquityinc.packer.model;

import lombok.Data;

/**
 * Model of possible items can be used for packing
 */
@Data
public class Item implements Comparable<Item> {
  private final int index;
  private final double weight;
  private final double cost;

    /**
     * compare by id for sorting ascending depends on index values
     *
     * @param item
     * @return
     */
    @Override
    public int compareTo(Item item) {
        return (this.index - item.getIndex());
  }
}
