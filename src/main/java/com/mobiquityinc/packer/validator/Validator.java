package com.mobiquityinc.packer.validator;

public interface Validator {
    int MAX_ITEM_SIZE = 15;
    double MAX_WEIGHT = 100d;
    double MAX_WEIGHT_LIMIT = 100d;
    double MAX_COST = 100d;


    String INVALID_ITEM_COST_MESSAGE =
            "Item cost cannot be more than %f , Wrong cost %f for item %s";

    String INVALID_ITEM_WEIGHT_MESSAGE =
            "Item weight cannot be more than %f, Wrong cost %f  for item %s";

    String INVALID_PACKAGE_ITEM_COUNT_MESSAGE =
            "Package item count cannot be more than %d,  package %s contains : %d items";
    String INVALID_PACKAGE_WEIGHT_MESSAGE =
            "Package weight cannot be more than %f, package weight limit is :%f for package %s";
}
