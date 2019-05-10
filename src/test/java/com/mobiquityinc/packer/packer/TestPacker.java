package com.mobiquityinc.packer.packer;

import com.mobiquityinc.packer.exception.APIException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

class TestPacker {

    @Test
    public void should_return_list() throws APIException {
        // given
        ClassLoader classLoader = TestPacker.class.getClassLoader();
        File GIVEN = new File(classLoader.getResource("given_test_case").getFile());

        String expected =
                "4"
                        + System.lineSeparator()
                        + "-"
                        + System.lineSeparator()
                        + "2,7"
                        + System.lineSeparator()
                        + "8,9";
        // when
        String result = Packer.pack(GIVEN.getAbsolutePath());

        // then
        Assertions.assertEquals(expected, result);
    }
}
