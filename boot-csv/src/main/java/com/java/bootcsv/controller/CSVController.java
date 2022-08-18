package com.java.bootcsv.controller;

import com.java.bootcsv.model.Customer;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class CSVController {

    private static final Logger logger = Logger.getLogger("CustomerController");

    @Value("${file.upload-dir}")
    String fileDir;

    @PostMapping("/uploadFile")
    public @ResponseBody
    ResponseEntity<String> uploadFile(@RequestParam("File") MultipartFile multipartFile) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String line = "";
        String customerId = "";
        InputStream inputStream = multipartFile.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        while ((line = br.readLine()) != null && (br.readLine() != "")) {
            customerId = line;
            System.out.println("Id ------>" + customerId);
        }

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8082/customer/getCustomer/";
        Customer customer = restTemplate.getForObject(url + customerId, Customer.class);

        FileWriter fileWriter = new FileWriter(fileDir);
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);

        // Create Mapping Strategy to arrange the
        // column name in order
        ColumnPositionMappingStrategy mappingStrategy = new ColumnPositionMappingStrategy();
        mappingStrategy.setType(Customer.class);

        // Arrange column name as provided in below array.
        String[] columns = new String[]{"customerId", "customerName"};
        mappingStrategy.setColumnMapping(columns);

        // Creating StatefulBeanToCsv object
        StatefulBeanToCsvBuilder<Customer> builder = new StatefulBeanToCsvBuilder<>(fileWriter);

        // Write list to StatefulBeanToCsv object
        StatefulBeanToCsv beanWriter = builder.withMappingStrategy(mappingStrategy).build();
        beanWriter.write(customerList);
        fileWriter.flush();
        fileWriter.close();

        return new ResponseEntity<>("The File Uploaded Successfully", HttpStatus.OK);

    }
}

