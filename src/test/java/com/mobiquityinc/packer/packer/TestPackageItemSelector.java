package com.mobiquityinc.packer.packer;

import com.mobiquityinc.packer.model.Item;
import com.mobiquityinc.packer.model.Package;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class TestPackageItemSelector {

    @Test
    void should_return_list() {
        // test case ==> 12 : (1,4,€6) (2,9,€9) (3,4,€6) : expected result 1,3 items

        // given
        List<Item> itemList =
                new ArrayList<>(
                        Arrays.asList(
                                new Item(1, 4.00, 6.00), new Item(2, 9.00, 9.00), new Item(3, 4.00, 6.00)));
        Package pack = new Package(12, itemList);
        PackageItemSelector packageItemSelector = new PackageItemSelector();

        List<Item> exceptedItems = Arrays.asList(new Item(1, 4.00, 6.00), new Item(3, 4.00, 6.00));

        // when
        List<Item> selectedItems = packageItemSelector.select(pack);

        // then
        Assertions.assertFalse(selectedItems.isEmpty());
        Assertions.assertIterableEquals(exceptedItems, selectedItems);
    }

    @Test
    void should_return_empty_list() {
        // test case ==> 1 : (1,4,€6) (2,9,€9)  : expected result empty List

        // given
        List<Item> itemList =
                new ArrayList<>(Arrays.asList(new Item(1, 4.00, 6.00), new Item(2, 9.00, 9.00)));
        Package pack = new Package(1, itemList);
        PackageItemSelector packageItemSelector = new PackageItemSelector();

        List<Item> exceptedItems = Collections.emptyList();

        // when
        List<Item> selectedItems = packageItemSelector.select(pack);

        // then
        Assertions.assertTrue(selectedItems.isEmpty());
    }
}
