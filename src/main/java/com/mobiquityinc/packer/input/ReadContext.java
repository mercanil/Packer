package com.mobiquityinc.packer.input;

import com.mobiquityinc.packer.exception.APIException;
import com.mobiquityinc.packer.model.Package;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReadContext {
    private InputReader inputReader;

    public List<Package> read(String absolutePath) throws APIException {
        return inputReader.read(absolutePath);
    }
}
