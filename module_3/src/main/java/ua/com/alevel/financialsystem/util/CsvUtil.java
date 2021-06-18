package ua.com.alevel.financialsystem.util;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import ua.com.alevel.financialsystem.entity.OperationJdbc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class CsvUtil {

    @SuppressWarnings("unchecked")
    public void createOrdering(List<OperationJdbc> operations, Integer income, Integer balance) {
        File tempDirectory = new File(System.getProperty("user.dir"));
        File csvFile = new File(tempDirectory.getAbsolutePath() + "\\module_3\\ordering.csv");

        try(Writer writer = new FileWriter(csvFile.getAbsolutePath(), false)) {
            StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .build();
            sbc.write(operations);
        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException | IOException e) {
            throw new RuntimeException(e);
        }

        File orderBalance = new File(tempDirectory.getAbsolutePath() + "\\module_3\\balance.txt");
        try {
            orderBalance.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try(FileWriter fileWriter = new FileWriter(orderBalance, false)) {
            fileWriter.write("Total income: " + income + System.lineSeparator());
            fileWriter.append("Balance: ").append(String.valueOf(balance));
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}