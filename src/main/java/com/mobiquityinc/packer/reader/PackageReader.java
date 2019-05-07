package com.mobiquityinc.packer.reader;

import com.mobiquityinc.packer.model.Package;

import java.util.List;

public interface PackageReader {
    List<Package> read(String path);
}
