package com.mobiquityinc.packer.model;

import java.util.Set;

public class Package {

  private final double weightLimit;
  private final Set<Item> items;

  /**
   * Constructs a new <tt>Package</tt> else throws IllegalArgumentException
   *
   * @param weightLimit Weight limit of Package
   * @param items       Packageable items
   */
  public Package(double weightLimit, Set<Item> items) {
    this.weightLimit = weightLimit;
    this.items = items;
  }

  public double getWeightLimit() {
    return weightLimit;
  }

  public Set<Item> getItems() {
    return items;
  }
}
