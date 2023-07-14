package com.esg.demo.ESG.Demo;

import com.esg.demo.ESG.Demo.csvutil.CsvUtils;
import com.esg.demo.ESG.Demo.customer.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CsvUtilsTest {


    private final CsvUtils csvUtils = new CsvUtils();

    private final String filePath = "./src/main/resources/csv/UserData.csv";
    private final String badFilePath = "./src/main/resources/csv/BadData.csv";

    /**
     * @verifies return list of customer
     */
    @Test
    public void getDataFromCsv_shouldReturnListOfCustomer() {
        List<Customer> customers = csvUtils.getDataFromCsv(filePath);

        Assertions.assertEquals(5, customers.size());
        Assertions.assertEquals(1, customers.get(0).getCustomerRef());
    }

    /**
     * @verifies throw exception with invalid data
     */
    @Test
    public void getDataFromCsv_shouldThrowExceptionWithInvalidData() {

        Assertions.assertThrows(NumberFormatException.class,
                () -> csvUtils.getDataFromCsv(badFilePath));
    }
}
