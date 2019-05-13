package com.mobiquityinc.packer.model;

import lombok.Data;

/**
 * Model of possible items can be used for packing
 */
@Data
public class Item {
  private final int index;
  private final double weight;
  private final double cost;

}
