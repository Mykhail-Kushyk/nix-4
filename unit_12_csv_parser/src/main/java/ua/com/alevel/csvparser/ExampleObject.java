package ua.com.alevel.csvparser;

import ua.com.alevel.csvparser.annotation.CsvName;

public class ExampleObject {

    @CsvName(name = "id")
    private int id;
    @CsvName(name = "name")
    private String name;
    @CsvName(name = "age")
    private int age;

    @Override
    public String toString() {
        return "ExampleObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}