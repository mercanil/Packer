package com.mobiquityinc.packer.packer;

import com.mobiquityinc.packer.model.Item;
import com.mobiquityinc.packer.model.Package;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class finds the best package having highest price in given weight limit
 */
public class PackageItemSelector {


    public List<Item> select(Package aPackage) {
        List<Item> itemList = aPackage.getItems();
        int numberOfThings = itemList.size();
        double weightLimit = aPackage.getWeightLimit();

        aPackage.getItems().sort(Comparator.comparingDouble(Item::getWeight));

        // we use a matrix to store the max value at each n-th item
        int[][] matrix = new int[numberOfThings + 1][(int) (weightLimit * 100 + 1)];


        int minWeight = (int) itemList.stream().min(Comparator.comparingDouble(Item::getWeight)).get().getWeight() * 100;

        // first line is initialized to 0
        for (int i = minWeight; i <= weightLimit * 100; i++) {
            matrix[0][i] = 0;
        }

        // we iterate on things
        for (int i = 1; i <= numberOfThings; i++) {
            // we iterate on each maximumWeight
            for (int j = minWeight; j <= weightLimit * 100; j++) {
                if (itemList.get(i - 1).getWeight() * 100 > j)
                    matrix[i][j] = matrix[i - 1][j];
                else
                    // we maximize value at this rank in the matrix
                    matrix[i][j] = Math.max(matrix[i - 1][j], (int) Math.floor(matrix[i - 1][j - (int) Math.ceil(itemList.get(i - 1).getWeight() * 100)]
                            + itemList.get(i - 1).getCost()));
            }
        }

        int res = matrix[numberOfThings][(int) (weightLimit * 100)];
        int w = (int) (weightLimit * 100);
        List<Item> thingsSolution = new ArrayList<>();

        for (int i = numberOfThings; i > 0 && res > 0; i--) {
            if (res != matrix[i - 1][w]) {
                thingsSolution.add(itemList.get(i - 1));
                // remove value and weight
                res -= Math.floor(itemList.get(i - 1).getCost());
                w -= Math.floor(itemList.get(i - 1).getWeight() * 100);
            }
        }


        //sort by index value
        List result = thingsSolution.stream().sorted(Comparator.comparingInt(Item::getIndex)).collect(Collectors.toList());


        return result;

    }



}
