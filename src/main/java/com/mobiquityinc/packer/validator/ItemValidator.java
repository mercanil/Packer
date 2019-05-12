package com.mobiquityinc.packer.validator;

import com.mobiquityinc.packer.exception.APIException;
import com.mobiquityinc.packer.model.Item;
import lombok.extern.slf4j.Slf4j;

/**
 * Validate Item for given constraints
 */
@Slf4j
public class ItemValidator implements Validator {

    private ItemValidator() {
    }

    /**
     * Checks if item cost is less than limit
     *
     * @param item argument for validation
     * @return boolean result of validation
     */
    public static boolean isCostValid(Item item) {
        return item.getCost() < MAX_COST;
    }

    /**
     * Checks if item weight is less than limit
     *
     * @param item argument for validation
     * @return boolean result of validation
     */
    public static boolean isWeightValid(Item item) {
        return item.getWeight() < MAX_WEIGHT;
    }

    /**
     * Throws exception if Item arguments are not valid
     *
     * @param aItem argument for validation
     * @throws APIException
     */
    public static void validateItem(Item aItem) throws APIException {
        log.debug("Validating item : " + aItem.toString());
        boolean isNotValid = isCostValid(aItem);
        if (!isNotValid) {
            throw new APIException(
                    String.format(
                            INVALID_ITEM_COST_MESSAGE, ItemValidator.MAX_COST, aItem.getWeight(), aItem));
        }

        isNotValid = isWeightValid(aItem);
        if (!isNotValid) {
            throw new APIException(
                    String.format(
                            INVALID_ITEM_WEIGHT_MESSAGE, ItemValidator.MAX_COST, aItem.getWeight(), aItem));
        }
    }
}
