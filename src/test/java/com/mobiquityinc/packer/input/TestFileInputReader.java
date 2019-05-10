package com.mobiquityinc.packer.input;

import com.mobiquityinc.packer.exception.APIException;
import com.mobiquityinc.packer.model.Item;
import com.mobiquityinc.packer.model.Package;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.catchThrowable;

class TestFileInputReader {

    @Test
    public void read_file_should_return_true() throws APIException {

        // given
        ClassLoader classLoader = TestFileInputReader.class.getClassLoader();
        File GIVEN = new File(classLoader.getResource("given_test_case").getFile());
        FileInputReader fileInputReader = new FileInputReader();
        Package p1 =
                new Package(
                        81,
                        Arrays.asList(
                                new Item(1, 53.38, 45),
                                new Item(2, 88.62, 98),
                                new Item(3, 78.48, 3),
                                new Item(4, 72.30, 76),
                                new Item(5, 30.18, 9),
                                new Item(6, 46.34, 48)));

        Package p2 = new Package(8, Arrays.asList(new Item(1, 15.3, 34)));

        Package p3 =
                new Package(
                        75,
                        Arrays.asList(
                                new Item(1, 85.31, 29),
                                new Item(2, 14.55, 74),
                                new Item(3, 3.98, 16),
                                new Item(4, 26.24, 55),
                                new Item(5, 63.69, 52),
                                new Item(6, 76.25, 75),
                                new Item(7, 60.02, 74),
                                new Item(8, 93.18, 35),
                                new Item(9, 89.95, 78)));

        Package p4 =
                new Package(
                        56,
                        Arrays.asList(
                                new Item(1, 90.72, 13),
                                new Item(2, 33.80, 40),
                                new Item(3, 43.15, 10),
                                new Item(4, 37.97, 16),
                                new Item(5, 46.81, 36),
                                new Item(6, 48.77, 79),
                                new Item(7, 81.80, 45),
                                new Item(8, 19.36, 79),
                                new Item(9, 6.76, 64)));

        List<Package> expected = Arrays.asList(p1, p2, p3, p4);

        // when
        List<Package> readResult = fileInputReader.read(GIVEN.getAbsolutePath());

        // THEN
        Assertions.assertEquals(readResult.size(), 4);
        Assertions.assertIterableEquals(expected, readResult);
    }

    @Test
    public void read_file_invalid_path() {
        // given
        FileInputReader fileInputReader = new FileInputReader();

        // when
        Throwable throwable = catchThrowable(() -> fileInputReader.read("invalid-path"));

        // then
        Assertions.assertNotNull(throwable);
        Assertions.assertTrue(throwable instanceof APIException);
    }

    @Test
    public void read_file_empty_path() {
        // given
        FileInputReader fileInputReader = new FileInputReader();

        // when
        Throwable throwable = catchThrowable(() -> fileInputReader.read(""));

        // then
        Assertions.assertNotNull(throwable);
        Assertions.assertTrue(throwable instanceof APIException);
    }

    @Test
    public void read_file_null_path() {
        // given
        FileInputReader fileInputReader = new FileInputReader();

        // when
        Throwable throwable = catchThrowable(() -> fileInputReader.read(null));

        // then
        Assertions.assertNotNull(throwable);
        Assertions.assertTrue(throwable instanceof APIException);
    }
}
