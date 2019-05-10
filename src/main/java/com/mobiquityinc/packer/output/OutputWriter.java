package com.mobiquityinc.packer.output;

import com.mobiquityinc.packer.model.Item;

import java.util.List;

public interface OutputWriter {
    String write(List<List<Item>> selectedItems);
}
