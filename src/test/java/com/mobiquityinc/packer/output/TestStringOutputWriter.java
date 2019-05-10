package com.mobiquityinc.packer.output;

import com.mobiquityinc.packer.model.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class TestStringOutputWriter {

    @Test
    void should_print_all_item() {
        // given
        List<Item> firstList = Arrays.asList(new Item(1, 2, 3), new Item(2, 4, 5));

        List<Item> secondList = Arrays.asList(new Item(1, 2, 3), new Item(2, 4, 5));

        List<List<Item>> selectedItems = Arrays.asList(firstList, secondList);
        String expected = "1,2" + System.lineSeparator() + "1,2";

        // when
        StringOutputWriter stringOutputWriter = new StringOutputWriter();
        String result = stringOutputWriter.write(selectedItems);

        // then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void should_print_dash_for_empty_list() {
        // given
        List<Item> firstList = Arrays.asList(new Item(1, 2, 3), new Item(2, 4, 5));

        List<Item> emptyList = Collections.EMPTY_LIST;

        List<Item> thirdList = Arrays.asList(new Item(1, 2, 3), new Item(2, 4, 5));

        List<List<Item>> selectedItems = Arrays.asList(firstList, emptyList, thirdList);
        String expected = "1,2" + System.lineSeparator() + "-" + System.lineSeparator() + "1,2";

        StringOutputWriter stringOutputWriter = new StringOutputWriter();

        // when
        String result = stringOutputWriter.write(selectedItems);

        // then
        Assertions.assertEquals(expected, result);
    }
}
