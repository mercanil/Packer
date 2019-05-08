package com.mobiquityinc.packer.validator;

import com.mobiquityinc.packer.model.Package;

import java.util.function.Predicate;

public class PackagePredicate {

    private PackagePredicate() {
    }

    public static Predicate<Package> weightLimitMorethan100() {
        return pack -> pack.getWeightLimit() > 100;
    }

    public static Predicate<Package> itemSizeMoreThan15() {
        return pack -> pack.getItems().size() > 15;
    }
}
