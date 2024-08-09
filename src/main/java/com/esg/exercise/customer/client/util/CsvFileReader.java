package com.esg.exercise.customer.client.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.esg.exercise.customer.client.model.Customer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CsvFileReader {
    public List<Customer> readFile(String filename) throws IOException {
        List<Customer> customers = new ArrayList<>();
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setDelimiter(',')
                .setHeader()
                .setSkipHeaderRecord(true)
                .build();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(filename));
                CSVParser csvParser = new CSVParser(reader, format);
        ) {
            for (CSVRecord csvRecord : csvParser) {
                customers.add(new Customer(
                        csvRecord.get("Customer_Ref"),
                        csvRecord.get("Customer_Name"),
                        csvRecord.get("Address_Line_1"),
                        csvRecord.get("Address_Line_2"),
                        csvRecord.get("Town"),
                        csvRecord.get("County"),
                        csvRecord.get("Country"),
                        csvRecord.get("Postcode")));
            }
        }

        return customers;
    }
}
