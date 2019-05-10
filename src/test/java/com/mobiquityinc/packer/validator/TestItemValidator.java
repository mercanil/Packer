package com.mobiquityinc.packer.validator;

import com.mobiquityinc.packer.exception.APIException;
import com.mobiquityinc.packer.model.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.catchThrowable;

class TestItemValidator {

    private final double INVALID_COST = ItemValidator.MAX_COST + 1;
    private final double INVALID_WEIGHT = ItemValidator.MAX_WEIGHT + 1;

    private final int VALID_INDEX = 1;
    private final double VALID_WEIGHT = 1;
    private final double VALID_COST = 1;

    @Test
    void should_return_true_valid_cost() {
        // given
        Item validItem = new Item(VALID_INDEX, VALID_WEIGHT, VALID_COST);
        // when
        boolean valid = ItemValidator.isCostValid(validItem);
        // then
        Assertions.assertTrue(valid);
    }

    @Test
    void should_return_false_invalid_cost() {
        // given
        Item validItem = new Item(VALID_INDEX, VALID_WEIGHT, INVALID_COST);
        // when
        boolean valid = ItemValidator.isCostValid(validItem);
        // then
        Assertions.assertFalse(valid);
    }

    @Test
    void should_return_true_valid_weight() {
        // given
        Item validItem = new Item(VALID_INDEX, VALID_WEIGHT, VALID_COST);
        // when
        boolean valid = ItemValidator.isWeightValid(validItem);
        // then
        Assertions.assertTrue(valid);
    }

    @Test
    void should_return_false_invalid_weight() {
        // given
        Item validItem = new Item(VALID_INDEX, INVALID_WEIGHT, VALID_COST);
        // when
        boolean valid = ItemValidator.isWeightValid(validItem);
        // then
        Assertions.assertFalse(valid);
    }

    @Test
    void should_return_true_valid_item() {
        // given
        Item validItem = new Item(VALID_INDEX, VALID_WEIGHT, VALID_COST);
        // when
        Throwable throwable = catchThrowable(() -> ItemValidator.validateItem(validItem));

        // then
        Assertions.assertNull(throwable);
    }

    @Test
    void should_return_throw_exception_invalid_weight() {
        // given
        Item validItem = new Item(VALID_INDEX, INVALID_WEIGHT, VALID_COST);
        // when
        Throwable throwable = catchThrowable(() -> ItemValidator.validateItem(validItem));

        // then
        Assertions.assertNotNull(throwable);
        Assertions.assertTrue(throwable instanceof APIException);
    }

    @Test
    void should_return_throw_exception_invalid_cost() {
        // given
        Item validItem = new Item(1, 1, INVALID_COST);
        // when
        Throwable throwable = catchThrowable(() -> ItemValidator.validateItem(validItem));

        // then
        Assertions.assertNotNull(throwable);
        Assertions.assertTrue(throwable instanceof APIException);
    }
}
