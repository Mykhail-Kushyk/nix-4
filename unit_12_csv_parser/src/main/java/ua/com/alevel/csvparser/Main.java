package ua.com.alevel.csvparser;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        URL resource = Main.class.getClassLoader().getResource("data.csv");
        File file = null;

        try {
            if (resource != null) {
                file = new File(resource.toURI());
            } else {
                throw new IOException();
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            List<String[]> records = reader.readAll();
            Table table = new Table(records);
            CsvMapper mapper = new CsvMapper();
            List<ExampleObject> example = mapper.mapCsvToList(records, ExampleObject.class);
            for (ExampleObject exampleObject:example) {
                System.out.println(exampleObject.toString());
            }
            System.out.println(table.get(3, "age"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}