package com.mobiquityinc.packer.model;

public class Item {
  private final int index;
  private final double weight;
  private final double cost;

  /**
   * Constructs a new <tt>Item</tt> if given parameters are correct else throws
   * IllegalArgumentException
   *
   * @param index  Index number of item
   * @param weight Weight of item
   * @param cost   Cost of item
   */
  public Item(int index, double weight, double cost) {
    this.index = index;
    this.weight = weight;
    this.cost = cost;
  }

  public double getWeight() {
    return weight;
  }

  public double getCost() {
    return cost;
  }
}
