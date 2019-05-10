package com.mobiquityinc.packer.model;

import lombok.Data;

import java.util.List;

/**
 *
 */
@Data
public class Package {

    private final double weightLimit;
    private final List<Item> items;

    /**
     * @return size of items in package
     */
    public int getItemSize() {
        return this.items == null ? 0 : this.items.size();
    }
}
