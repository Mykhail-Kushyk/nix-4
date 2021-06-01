package ua.com.alevel.jdbc;

import ua.com.alevel.jdbc.entity.Location;
import ua.com.alevel.jdbc.entity.Problem;
import ua.com.alevel.jdbc.entity.Route;
import ua.com.alevel.jdbc.init.Inserter;
import ua.com.alevel.module.third.Graph;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Runner {

    public static void main(String[] args) {
        Properties props = new Properties();
        String fileName = "jdbc.properties";
        try (InputStream inputStream = Runner.class.getClassLoader().getResourceAsStream(fileName)) {
            props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(props.getProperty("url"), props)) {
            Location[] locations = {
                    new Location(1, "Kiev"),
                    new Location(2, "Kharkov"),
                    new Location(3, "Lvov"),
                    new Location(4, "Odessa")
            };
            Graph graph = new Graph(locations.length);
            Inserter inserter = new Inserter(connection);
            inserter.insertLocations(locations, graph);
            Route[] routes = {
              new Route(0, 1, 2, 479),
              new Route(1, 1, 3, 534),
              new Route(2, 1, 4, 475),
              new Route(3, 2, 3, 1009),
              new Route(4, 2, 4, 737),
              new Route(5, 3, 4, 797)
            };
            inserter.insertRoutes(routes, graph);
            Problem[] problems = {
              new Problem(0, 2, 3),
              new Problem(1, 4, 3)
            };
            inserter.insertProblems(problems);
            Resolver resolver = new Resolver(connection);
            resolver.resolveAllProblems(graph);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}