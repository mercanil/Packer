package com.mobiquityinc.packer.packer;

import com.mobiquityinc.packer.model.Item;
import com.mobiquityinc.packer.model.Package;
import com.mobiquityinc.packer.validator.PackageValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class finds the best package having highest price in given weight limit
 */
public class PackageItemSelector {

    /**
     * Selects best packaging option from given possible items
     *
     * @param aPackage
     * @return List of selected items for packaging
     */
    public List<Item> select(Package aPackage) {
        List<ArrayList<Item>> validCombinations = createValidCombinations(aPackage);

        if (validCombinations.size() == 0) {
            return Collections.emptyList();
        } else {
            List<Item> bestPackage = getBestPackageItems(validCombinations);
            Collections.sort(bestPackage);
            return bestPackage;
        }
    }

    /**
     * Creates all valid combinations for a given package
     *
     * @param aPackage
     * @return possible valid combinations
     */
    private List<ArrayList<Item>> createValidCombinations(Package aPackage) {
        List<ArrayList<Item>> validPackages = new ArrayList<>();
        // remove item has more weight than package weight limit.
        // they will never be in the list.
        List<Item> items = getItemsWeightLessThanLimit(aPackage);
        // looping all items in a package & make valid combinations
        for (Item item : items) {
            ArrayList<Item> currentItemListForPackage = new ArrayList<>();
            currentItemListForPackage.add(item);
            int validPackageSize = validPackages.size();
            for (int j = 0; j < validPackageSize; j++) {
                ArrayList<Item> combineWithValidPackageItem = new ArrayList<>(validPackages.get(j));
                combineWithValidPackageItem.add(item);
                // Add package if there is space for that package
                if (isValidPackage(combineWithValidPackageItem, aPackage.getWeightLimit())) {
                    validPackages.add(combineWithValidPackageItem);
                }
            }
            validPackages.add(currentItemListForPackage);
        }
        return validPackages;
    }

    /**
     * remove item has more weight than package weight limit. they will never be in the list.
     *
     * @param Package all items and weight limit will be checked
     * @return package weight limit items
     */
    private List<Item> getItemsWeightLessThanLimit(Package aPackage) {
        return aPackage.getItems().stream()
                .filter(item -> item.getWeight() < aPackage.getWeightLimit())
                .collect(Collectors.toList());
    }

    /**
     * Checks the combination weight is less then weight limit
     *
     * @param currentItemListForPackage list of items in package
     * @param packageWeightLimit weight limit of package
     * @return
     */
    private boolean isValidPackage(ArrayList<Item> currentItemListForPackage, double packageWeightLimit) {
        return packageWeightLimit >= getTotalWeight(currentItemListForPackage);
    }

    /**
     * Finds the best package from the combinations
     *
     * @param combinations
     * @return bestPackage
     */
    private List<Item> getBestPackageItems(List<ArrayList<Item>> combinations) {
        List<Item> bestPackage = new ArrayList<>();
        double bestPackagePrice = 0;
        //Possible maximum weight limit
        double bestPackageWeight = PackageValidator.MAX_WEIGHT_LIMIT;
        for (List<Item> currentCombination : combinations) {
            double combinationWeight = getTotalWeight(currentCombination);
            double combinationPrice = getTotalPrice(currentCombination);
            // get more valuable package
            if (combinationPrice > bestPackagePrice) {
                bestPackage = currentCombination;
                bestPackagePrice = combinationPrice;
                bestPackageWeight = combinationWeight;
            } else if (combinationPrice == bestPackagePrice) {
                // get lighter package if prices are same
                if (combinationWeight < bestPackageWeight) {
                    bestPackage = currentCombination;
                    bestPackagePrice = combinationPrice;
                    bestPackageWeight = combinationWeight;
                }
            }
        }
        return bestPackage;
    }

    /**
     * returns the weight for given Item list
     *
     * @param items
     * @return weight
     */
    private double getTotalWeight(List<Item> items) {
        return items.stream().mapToDouble(Item::getWeight).sum();
    }

    /**
     * return the price for given Item list
     *
     * @param items
     * @return price
     */
    private double getTotalPrice(List<Item> items) {
        return items.stream().mapToDouble(Item::getCost).sum();
  }
}
