package com.mobiquityinc.packer.input;

import com.mobiquityinc.packer.exception.APIException;
import com.mobiquityinc.packer.model.Package;

import java.util.List;

public interface InputReader {
    List<Package> read(String absolutePath) throws APIException;
}
