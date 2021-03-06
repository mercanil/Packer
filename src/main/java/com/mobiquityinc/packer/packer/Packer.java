package com.mobiquityinc.packer.packer;

import com.mobiquityinc.packer.exception.APIException;
import com.mobiquityinc.packer.input.FileInputReader;
import com.mobiquityinc.packer.input.ReadContext;
import com.mobiquityinc.packer.model.Item;
import com.mobiquityinc.packer.model.Package;
import com.mobiquityinc.packer.output.StringOutputWriter;
import com.mobiquityinc.packer.output.WriteContext;
import com.mobiquityinc.packer.validator.PackageValidator;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Packer API entry point
 */
@Slf4j
public class Packer {
    /**
     * Entrance point of application. API consumer should provide absolute path of file contains
     * package information.
     *
     * @param fileName absolute path of string to process
     * @return output string. Contains information about selected item for package
     * @throws APIException If given file path is wrong , null or items are invalid in the file .
     */
    public static String pack(String fileName) throws APIException {

        WriteContext writeContext = new WriteContext();
        writeContext.setOutputWriter(new StringOutputWriter());

        ReadContext readContext = new ReadContext();
        readContext.setInputReader(new FileInputReader());

        PackageItemSelector packageItemSelector = new PackageItemSelector();

        log.info("Processing file " + fileName);
        List<Package> packages = readContext.read(fileName);
        for (Package aPackage : packages) {
            PackageValidator.validatePackage(aPackage);
        }
        log.debug("Processed  file " + fileName + ". Package size" + packages.size());

        List<List<Item>> resultList =
                packages.stream().map(packageItemSelector::select).collect(Collectors.toList());

        log.info("Packing completed " + resultList.size() + " items processed.");

        return writeContext.write(resultList);
  }

}
