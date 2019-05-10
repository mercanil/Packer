package com.mobiquityinc.packer.validator;

import com.mobiquityinc.packer.exception.APIException;
import com.mobiquityinc.packer.model.Item;
import com.mobiquityinc.packer.model.Package;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Validate Package for given constraints
 */
@Slf4j
public class PackageValidator {
    public static final int MAX_ITEM_SIZE = 15;
    public static final double MAX_WEIGHT_LIMIT = 100d;
    private static final String INVALID_PACKAGE_ITEM_COUNT_MESSAGE =
            "Package item count cannot be more than %d,  package %s contains : %d items";
    private static final String INVALID_PACKAGE_WEIGHT_MESSAGE =
            "Package weight cannot be more than %f, package weight limit is :%f for package %s";

    private PackageValidator() {
    }

    /**
     * Checks if package weight limit is less than limit
     *
     * @param pack
     * @return boolean result of validation
     */
    public static boolean isWeightValid(Package pack) {
        return pack.getWeightLimit() < MAX_WEIGHT_LIMIT;
    }

    /**
     * Checks if package item size is less than limit
     *
     * @param pack
     * @return boolean result of validation
     */
    public static boolean isItemSizeValid(Package pack) {
        return pack.getItems().size() < MAX_ITEM_SIZE;
    }

    /**
     * Throws exception if Package arguments are not valid
     *
     * @param aPackage
     * @throws APIException
     */
    public static void validatePackage(Package aPackage) throws APIException {
        log.debug("Validation package:" + aPackage);
        boolean isNotValid = isItemSizeValid(aPackage);
        if (!isNotValid) {
            throw new APIException(
                    String.format(
                            INVALID_PACKAGE_ITEM_COUNT_MESSAGE,
                            PackageValidator.MAX_ITEM_SIZE,
                            aPackage,
                            aPackage.getItemSize()));
        }

        isNotValid = PackageValidator.isWeightValid(aPackage);
        if (!isNotValid) {
            throw new APIException(
                    String.format(
                            INVALID_PACKAGE_WEIGHT_MESSAGE,
                            PackageValidator.MAX_WEIGHT_LIMIT,
                            aPackage.getWeightLimit(),
                            aPackage));
        }
        List<Item> items = aPackage.getItems();
        for (Item item : items) {
            ItemValidator.validateItem(item);
        }
    }
}
