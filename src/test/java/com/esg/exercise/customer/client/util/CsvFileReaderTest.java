package com.esg.exercise.customer.client.util;

import com.esg.exercise.customer.client.model.Customer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvFileReaderTest {

    @Test
    public void givenCSVFile_whenRead_thenContentsAsExpected() throws IOException {
        CsvFileReader csvFileReader = new CsvFileReader();
        List<Customer> customers = csvFileReader.readFile("data\\customer.csv");

        assertEquals(3, customers.size());
    }
}
