package ua.com.alevel.module;

import ua.com.alevel.module.first.DateParser;
import ua.com.alevel.module.second.FinderUnique;
import ua.com.alevel.module.third.Graph;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Application {

    private final String firstInputFile = "./src/main/resources/input/firstInputData";
    private final String secondInputFile = "./src/main/resources/input/secondInputData";
    private final String thirdInputFile = "./src/main/resources/input/thirdInputData";
    private final String firstOutputFile = "./src/main/resources/output/firstOutputData";
    private final String secondOutputFile = "./src/main/resources/output/secondOutputData";
    private final String thirdOutputFile = "./src/main/resources/output/thirdOutputData";

    public void run() {
        runFirst();
        runSecond();
        runThird();
        System.out.println();
        System.out.println("----------------------------------------------------------------");
        System.out.println();
        System.out.println("    You can show input data in resources/input directory!");
        System.out.println();
        System.out.println("    And you can show results in resources/output directory!");
        System.out.println();
        System.out.println("----------------------------------------------------------------");
    }

    private void runFirst() {
        List<String> lines = new ArrayList<>();
        File file = new File(firstInputFile);
        try (Stream<String> linesStream = Files.lines(file.toPath())){
            lines = linesStream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileWriter writer = new FileWriter(firstOutputFile)){
            DateParser parser = new DateParser();
            for (int i = 0; i < lines.size(); i++) {
                writer.write(parser.parseDate(lines.get(i)));
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void runSecond() {
        List<String> lines = new ArrayList<>();
        File file = new File(secondInputFile);
        try (Stream<String> linesStream = Files.lines(file.toPath())){
            lines = linesStream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileWriter writer = new FileWriter(secondOutputFile)){
            writer.write(FinderUnique.findFirstUnique(lines));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void runThird() {
        File file = new File(thirdInputFile);
        try (Scanner reader = new Scanner(file);
             FileWriter writer = new FileWriter(thirdOutputFile)){
            Graph graph = new Graph(Integer.parseInt(reader.nextLine()));
            for (int i = 0; i < graph.getTotalVerts(); i++) {
                graph.addVertex(reader.nextLine());
                int number = Integer.parseInt(reader.nextLine());
                for (int j = 0; j < number; j++) {
                    String[] data = reader.nextLine().split("\\s");
                    graph.addEdge(Integer.parseInt(data[0]),
                            Integer.parseInt(data[1]),
                            Integer.parseInt(data[2]));
                }
            }
            int number = Integer.parseInt(reader.nextLine());
            for (int i = 0; i < number; i++) {
                String[] cities = reader.nextLine().split("\\s");
                writer.write(cities[0] + " -> " + cities[1] + " - ");
                writer.write(String.valueOf(graph.findShortestPathBetweenCities(cities[0], cities[1])));
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}