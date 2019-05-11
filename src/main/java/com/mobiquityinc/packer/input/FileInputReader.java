package com.mobiquityinc.packer.input;

import com.mobiquityinc.packer.exception.APIException;
import com.mobiquityinc.packer.model.Item;
import com.mobiquityinc.packer.model.Package;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class reads file and returns object model of Package
 */
@Slf4j
public class FileInputReader implements InputReader {

    private static final String INVALID_FILE_PATH_MESSAGE = "Invalid file path: %s ";
    private static final String FILE_NOT_FOUND = "File not found :%s ";
    private static final String GENERAL_EXCEPTION = "Exception occurred reading file: %s";
    private static final String FORMAT_EXCEPTION = "File format is incorrect. Please check file: %s";

    private static final String LIMIT_SEPARATOR = " : ";
    private static final String WHITESPACE_REGEX = "\\s+";
    private static final String COMMA_SEPARATOR = ",";

    /**
     * Reads <tt>file</tt>from given <tt>absolute</tt> path and reads content of this file line by
     * line return <tt>Package</tt> model of given text
     *
     * @param absolutePath absolute path of file
     * @return List<Package> List of packages read from file line by line
     */
    @Override
    public List<Package> read(String absolutePath) throws APIException {

        isFilePathValid(absolutePath);

        Path path = Paths.get(absolutePath);
        isFileExists(absolutePath, path);

        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            return br.lines().map(this::processLine).collect(Collectors.toList());
        } catch (IOException e) {
            throw new APIException(String.format(GENERAL_EXCEPTION, absolutePath));
        } catch (IllegalArgumentException e) {
            throw new APIException(String.format(FORMAT_EXCEPTION, absolutePath));
        }
    }

    /**
     * Check the given file path valid
     *
     * @param absolutePath absolute path of file
     * @throws APIException
     */
    private void isFilePathValid(String absolutePath) throws APIException {
        log.debug("Reading file " + absolutePath);
        if (absolutePath == null || absolutePath.isEmpty()) {
            throw new APIException(String.format(INVALID_FILE_PATH_MESSAGE, absolutePath));
        }
    }


    /**
     * Check the given file exists
     *
     * @param absolutePath absolute path of file
     * @param path         path of the absolute path of file
     * @throws APIException
     */
    private void isFileExists(String absolutePath, Path path) throws APIException {
        if (!path.toFile().exists()) {
            throw new APIException(String.format(FILE_NOT_FOUND, absolutePath));
        }
    }

    /**
     * Reads each line of file and creates Package
     *
     * @param line processing line of file
     * @return Package Model of line
     */
    private Package processLine(String line) {
        log.debug("Processing Line" + line);
        String[] packageAndItem = line.split(LIMIT_SEPARATOR);
        double maximumLimitOfPackage = getMaximumLimitOfPackage(packageAndItem[0]);
        List<Item> packageItemList = getPackageItemList(packageAndItem[1]);
        return new Package(maximumLimitOfPackage, packageItemList);
    }

    /**
     * gets first part of file WEIGHT_LIMIT :
     *
     * @param firstPartOfFile (before ":" ) contains maximum weight of package
     * @return Double conversion of string.
     */
    private double getMaximumLimitOfPackage(String firstPartOfFile) {
        return Double.parseDouble(firstPartOfFile);
    }

    /**
     * gets second part of line in text file. List of items creates Set of item from given string.
     *
     * @param itemAsString rest of the file. This string contains set of items
     * @return Object representation of items
     */
    private List<Item> getPackageItemList(String itemAsString) {
        log.debug("Processing item" + itemAsString);
        String[] items = itemAsString.split(WHITESPACE_REGEX);

        return Stream.of(items).map(this::getItemFromString).collect(Collectors.toList());
    }

    /**
     * pattern of string : (INDEX_NUMBER ,WEIGHT ,COST ) cost contains currency symbol. Removed while
     * reading value. at the end of amount.
     *
     * @param itemStr single definition of item
     * @return Item String to Item conversion
     */
    private Item getItemFromString(String itemStr) {
        // remove ( and ) chars from string
        String[] split = itemStr.substring(1, itemStr.length() - 1).split(COMMA_SEPARATOR);
        int index = Integer.parseInt(split[0]);
        double weight = Double.parseDouble(split[1]);
        double cost = Double.parseDouble(split[2].substring(1));
        return new Item(index, weight, cost);
    }
}
