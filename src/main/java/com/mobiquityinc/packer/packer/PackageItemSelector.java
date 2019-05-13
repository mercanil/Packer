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


    /**
     * Select best item combination from given package
     *
     * @param aPackage package contains weight limit and possible items
     * @return list of items with selected to be packaged
     */
    public List<Item> select(Package aPackage) {
        List<Item> itemList = aPackage.getItems();
        int numberOfThings = itemList.size();
        double weightLimit = aPackage.getWeightLimit();

        aPackage.getItems().sort(Comparator.comparingDouble(Item::getWeight));


        int[][] matrix = createMatrix(itemList, weightLimit);
        int minWeight = calculateMinWeight(itemList);

        for (int itemIndex = 1; itemIndex <= numberOfThings; itemIndex++) {
            for (int j = minWeight; j <= weightLimit * 100; j++) {
                if (itemList.get(itemIndex - 1).getWeight() * 100 > j)
                    matrix[itemIndex][j] = matrix[itemIndex - 1][j];
                else
                    // we maximize value at this rank in the matrix
                    matrix[itemIndex][j] = Math.max(matrix[itemIndex - 1][j], (int) Math.floor(matrix[itemIndex - 1][j - (int) Math.ceil(itemList.get(itemIndex - 1).getWeight() * 100)]
                            + itemList.get(itemIndex - 1).getCost()));
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

    /**
     * Create initial matrix from given itemlist and weight limit*
     *
     * @param itemList    list of items in package
     * @param weightLimit maximum weight that package can store
     * @return two dimension int array for weight and item matrix
     */

    private int[][] createMatrix(List<Item> itemList, double weightLimit) {
        int numberOfThings = itemList.size();
        // we use a matrix to store the max value at each n-th item
        int[][] matrix = new int[numberOfThings + 1][(int) (weightLimit * 100 + 1)];


        int minWeight = calculateMinWeight(itemList);

        // first line is initialized to 0
        for (int i = minWeight; i <= weightLimit * 100; i++) {
            matrix[0][i] = 0;
        }
        return matrix;
    }

    /**
     * return minimum weight of item from given item list
     *
     * @param itemList item list for selecting minimum weight
     * @return minimum weight in item list
     */
    private int calculateMinWeight(List<Item> itemList) {
        return (int) itemList.stream().min(Comparator.comparingDouble(Item::getWeight)).get().getWeight() * 100;

    }

}
