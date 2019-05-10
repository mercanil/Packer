package com.mobiquityinc.packer.validator;

import com.mobiquityinc.packer.exception.APIException;
import com.mobiquityinc.packer.model.Item;
import com.mobiquityinc.packer.model.Package;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.catchThrowable;

class TestPackageValidator {

    private final double INVALID_WEIGHT_LIMIT = PackageValidator.MAX_WEIGHT_LIMIT + 1;
    private final int INVALID_ITEM_SIZE = PackageValidator.MAX_ITEM_SIZE + 1;

    private final double VALID_WEIGHT_LIMIT = 1;

    @Test
    void should_pass_valid_weight_limit() {
        // given
        Package validWeightAndSize = new Package(VALID_WEIGHT_LIMIT, Collections.emptyList());
        // when
        boolean valid = PackageValidator.isWeightValid(validWeightAndSize);
        // then
        Assertions.assertTrue(valid);
    }

    @Test
    void should_fail_invalid_weight_limit() {
        // given
        Package aPackage = new Package(INVALID_WEIGHT_LIMIT, Collections.emptyList());

        // when
        boolean invalid = PackageValidator.isWeightValid(aPackage);

        // then
        Assertions.assertFalse(invalid);
    }

    @Test
    void should_pass_valid_size_limit() {
        // given
        Package aPackage = new Package(1, Collections.emptyList());
        // when
        boolean valid = PackageValidator.isItemSizeValid(aPackage);
        // then
        Assertions.assertTrue(valid);
    }

    @Test
    void should_fail_invalid_size_limit() {
        // given
        Item validItem = new Item(1, 1, 1);
        List<Item> packageWithInvalidItemSize = Collections.nCopies(INVALID_ITEM_SIZE, validItem);
        Package aPackage = new Package(1, packageWithInvalidItemSize);

        // when
        boolean invalid = PackageValidator.isItemSizeValid(aPackage);

        // then
        Assertions.assertFalse(invalid);
    }

    @Test
    void should_pass_valid_package() {
        // given
        Package aPackage = new Package(1, Collections.emptyList());
        // when
        Throwable throwable = catchThrowable(() -> PackageValidator.validatePackage(aPackage));

        // then
        Assertions.assertNull(throwable);
    }

    @Test
    void should_return_throw_exception_invalid_size() {
        // given
        Item validItem = new Item(1, 1, 1);
        List<Item> packageWithInvalidItemSize = Collections.nCopies(INVALID_ITEM_SIZE, validItem);
        Package invalidPackage = new Package(1, packageWithInvalidItemSize);
        // when
        Throwable throwable = catchThrowable(() -> PackageValidator.validatePackage(invalidPackage));

        // then
        Assertions.assertNotNull(throwable);
        Assertions.assertTrue(throwable instanceof APIException);
    }

    @Test
    void should_return_throw_exception_invalid_weight() {
        // given
        Package aPackage = new Package(INVALID_WEIGHT_LIMIT, Collections.emptyList());

        // when
        Throwable throwable = catchThrowable(() -> PackageValidator.validatePackage(aPackage));

        // then
        Assertions.assertNotNull(throwable);
        Assertions.assertTrue(throwable instanceof APIException);
    }
}
