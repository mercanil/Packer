package com.mobiquityinc.packer.output;

import com.mobiquityinc.packer.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Creates String output of selected items for package
 */
public class StringOutputWriter implements OutputWriter {
    private static final String EMPTY_PACKAGE = "-";

    /**
     * Create String from items. Gets index number and separate them by "," if item is empty writes
     * "-"
     *
     * @param selectedItems
     * @return String for sent items. If list is empty
     */
    @Override
    public String write(List<List<Item>> selectedItems) {

        List<String> results = new ArrayList<>();
        selectedItems.forEach(
                itemList -> {
                    if (itemList.size() == 0) {
                        results.add(EMPTY_PACKAGE);
                    } else {
                        String itemIndexes =
                                itemList.stream()
                                        .map(item -> String.valueOf((item.getIndex())))
                                        .collect(Collectors.joining(","));
                        results.add(itemIndexes);
                    }
                });

        return results.stream().collect(Collectors.joining(System.lineSeparator()));
    }
}
