package com.mobiquityinc.packer.validator;

import com.mobiquityinc.packer.model.Item;

import java.util.function.Predicate;

public class ItemPredicate {
    private ItemPredicate() {
    }

    public static Predicate<Item> costIsMorethan100() {
        return item -> item.getCost() > 100;
    }

    public static Predicate<Item> weightIsMorehan100() {
        return item -> item.getWeight() > 100;
    }
}
