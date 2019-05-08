package com.mobiquityinc.packer.reader;

import com.mobiquityinc.packer.exception.APIException;
import com.mobiquityinc.packer.model.Item;
import com.mobiquityinc.packer.model.Package;
import com.mobiquityinc.packer.validator.ItemPredicate;
import com.mobiquityinc.packer.validator.PackagePredicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** */
public class FilePackageReader implements PackageReader {

  private static final Logger LOGGER = LoggerFactory.getLogger(FilePackageReader.class);

  private static final String LIMIT_SEPARATOR = " : ";
  private static final String WHITESPACE_REGEX = "\\s+";
  private static final String COMMA_SEPERATOR = ",";

  /**
   * Reads <tt>file</tt>from given <tt>absolute</tt> path and reads content of this file line by
   * line return <tt>Package</tt> model of given text
   *
   * @param absolutePath given absolute path of file
   * @return List<Package>
   */
  public List<Package> read(String absolutePath) {

    if (absolutePath == null || absolutePath.isEmpty()) {
      throw new APIException("Invalid file path: " + absolutePath);
    }

    Path path = Paths.get(absolutePath);
    if (!path.toFile().exists()) {
      throw new APIException("File does not exist: " + absolutePath);
    }

    try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
      List<Package> collect = br.lines().map(this::processLine).collect(Collectors.toList());
      validatePackage(collect);
      return collect;

    } catch (IOException e) {
      throw new APIException("File absolutePath exception");
    }
  }

  /**
   * @param line processing line of file
   * @return Package
   */
  private Package processLine(String line) {
    String[] packageAndItem = line.split(LIMIT_SEPARATOR);
    double maximumLimitOfPackage = getMaximumLimitOfPackage(packageAndItem[0]);
    Set<Item> packageableItems = getPossibleItems(packageAndItem[1]);
    return new Package(maximumLimitOfPackage, packageableItems);
  }

  /**
   * @param firstPartOfFile (before ":" ) contains maximum weight of package
   * @return Double conversion of string.
   */
  private double getMaximumLimitOfPackage(String firstPartOfFile) {
    return Double.parseDouble(firstPartOfFile);
  }

  /**
   * gets second part of line in text file. List of items creates Set of item from given string.
   *
   * @param itemString rest of the file. This stringcontains set of items
   * @return Object representation of items
   * @throws IllegalArgumentException
   */
  private Set<Item> getPossibleItems(String itemString) {
    String[] items = itemString.split(WHITESPACE_REGEX);
    Set<Item> itemsFromString =
            Stream.of(items).map(this::getItemsFromString).collect(Collectors.toSet());
    validateItems(itemsFromString);
    return itemsFromString;
  }

  /**
   * pattern of string : (INDEX_NUMBER ,WEIGHT ,COST ) cost contains currency symbol. Removed while
   * reading value. TODO: should add multi currency support be careful some currency symbol may be
   * at the end of amount.
   *
   * @param itemStr single definition of item
   * @return Item String to Item conversion
   */
  private Item getItemsFromString(String itemStr) {
    // remove ( and ) chars from string
    String[] split = itemStr.substring(1, itemStr.length() - 1).split(COMMA_SEPERATOR);
    int index = Integer.parseInt(split[0]);
    double weight = Double.parseDouble(split[1]);
    double cost = Double.parseDouble(split[2].substring(1));

    return new Item(index, weight, cost);
  }

  private void validateItems(Set<Item> itemSet) {
    boolean anyItemCostMoreThan100 = itemSet.stream().anyMatch(ItemPredicate.costIsMorethan100());
    if (anyItemCostMoreThan100) {
      throw new IllegalArgumentException();
    }

    boolean anyItemWeightMoreThan100 =
            itemSet.stream().anyMatch(ItemPredicate.weightIsMorehan100());
    if (anyItemWeightMoreThan100) {
      throw new IllegalArgumentException();
    }
  }

  private void validatePackage(List<Package> collect) {
    boolean itemSizeMoreThan15 = collect.stream().anyMatch(PackagePredicate.itemSizeMoreThan15());
    if (itemSizeMoreThan15) {
      throw new IllegalArgumentException();
    }

    boolean packageWeghtMoreThan100 =
            collect.stream().anyMatch(PackagePredicate.weightLimitMorethan100());
    if (packageWeghtMoreThan100) {
      throw new IllegalArgumentException();
    }
  }
}
