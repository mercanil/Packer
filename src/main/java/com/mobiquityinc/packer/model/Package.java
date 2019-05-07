package com.mobiquityinc.packer.model;

import java.util.Set;

public class Package {

    private final double weightLimit;
    private final Set<Item> items;


    /**
     * Constructs a new <tt>Package</tt>
     * else throws IllegalArgumentException
     *
     * @param weightLimit Weight limit of Package
     * @param items       Packageable items
     * @throws IllegalArgumentException if item weight is more than specific number
     */
    public Package(double weightLimit, Set<Item> items) {

        if (weightLimit > 100) {
            throw new IllegalArgumentException("Invalid Package Weight Limit");
        }

        if (items.size() > 15) {
            throw new IllegalArgumentException("Invalid size of element");
        }
        this.weightLimit = weightLimit;
        this.items = items;
    }
}
