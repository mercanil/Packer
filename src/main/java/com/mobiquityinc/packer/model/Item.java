package com.mobiquityinc.packer.model;

public class Item {
    private final int index;
    private final double weight;
    private final double cost;


    /**
     * Constructs a new <tt>Item</tt> if given parameters are correct
     * else throws IllegalArgumentException
     *
     * @param index  Index number of item
     * @param weight Weight of item
     * @param cost   Cost of item
     * @throws IllegalArgumentException if item weight is more than specific number
     *                                  or item cost is morer than specific amount
     */
    public Item(int index, double weight, double cost) {

        if (weight > 100) {
            throw new IllegalArgumentException("Invalid Item Weight");
        }
        if (cost > 100) {
            throw new IllegalArgumentException("Invalid Item Cost");
        }

        this.index = index;
        this.weight = weight;
        this.cost = cost;
    }


}
