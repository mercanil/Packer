package com.mobiquityinc.packer.output;

import com.mobiquityinc.packer.model.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WriteContext {
    private OutputWriter outputWriter;

    public String write(List<List<Item>> selectedItems) {
        return outputWriter.write(selectedItems);
    }
}
