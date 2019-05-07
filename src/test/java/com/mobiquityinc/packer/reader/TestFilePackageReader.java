package com.mobiquityinc.packer.reader;

import com.mobiquityinc.packer.exception.APIException;
import com.mobiquityinc.packer.model.Package;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

public class TestFilePackageReader {
    private static File GIVEN;
    private static File WRONG_ITEM_COST;
    private static File WRONG_ITEM_WEIGHT;
    private static File WRONG_PACKAGE_WEIGHT;
    private static File WRONG_ITEM_COUNT;


    @BeforeAll
    public static void readFileBeforeClass() throws Exception {
        ClassLoader classLoader = TestFilePackageReader.class.getClassLoader();
        GIVEN = new File(classLoader.getResource("given_test").getFile());
        WRONG_ITEM_COST = new File(classLoader.getResource("item_cost>100").getFile());
        WRONG_ITEM_WEIGHT = new File(classLoader.getResource("item_weight>100").getFile());
        WRONG_PACKAGE_WEIGHT = new File(classLoader.getResource("package_weight>100").getFile());
        WRONG_ITEM_COUNT = new File(classLoader.getResource("16_item_in_file").getFile());
    }

    @Test
    public void testReadFile() {
        FilePackageReader filePackageReader = new FilePackageReader();
        List<Package> read = filePackageReader.read(GIVEN.getAbsolutePath());
        Assertions.assertEquals(read.size(), 4);
    }

    @Test
    public void testReadFile_Invalid_Path() {
        FilePackageReader filePackageReader = new FilePackageReader();
        Assertions.assertThrows(APIException.class, () -> filePackageReader.read("invalid-path"));

    }


    @Test
    public void testFile_ExpectItemException_Cost() {
        FilePackageReader filePackageReader = new FilePackageReader();
        Assertions.assertThrows(IllegalArgumentException.class, () -> filePackageReader.read(WRONG_ITEM_COST.getAbsolutePath()));
    }

    @Test
    public void testFile_ExpectItemException_Weight() {
        FilePackageReader filePackageReader = new FilePackageReader();
        Assertions.assertThrows(IllegalArgumentException.class, () -> filePackageReader.read(WRONG_ITEM_WEIGHT.getAbsolutePath()));
    }

    @Test
    public void testFile_ExpectPackageException_Weight() {
        FilePackageReader filePackageReader = new FilePackageReader();
        Assertions.assertThrows(IllegalArgumentException.class, () -> filePackageReader.read(WRONG_PACKAGE_WEIGHT.getAbsolutePath()));
    }

    @Test
    public void testFile_ExpectItemException_Count() {
        FilePackageReader filePackageReader = new FilePackageReader();
        Assertions.assertThrows(IllegalArgumentException.class, () -> filePackageReader.read(WRONG_ITEM_COUNT.getAbsolutePath()));
    }

}
