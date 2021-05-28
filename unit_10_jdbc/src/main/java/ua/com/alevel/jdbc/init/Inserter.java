package ua.com.alevel.jdbc.init;

import ua.com.alevel.jdbc.entity.Location;
import ua.com.alevel.jdbc.entity.Problem;
import ua.com.alevel.jdbc.entity.Route;
import ua.com.alevel.jdbc.entity.Solution;
import ua.com.alevel.module.third.Graph;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Inserter {

    private Connection connection;

    public Inserter(Connection connection) {
        this.connection = connection;
    }

    public void insertLocations(Location[] locations, Graph graph) {
        try (PreparedStatement insertLocation = connection.prepareStatement(
                "INSERT INTO Location VALUES(?, ?)"
        )) {
            for (int i = 0; i < locations.length; i++) {
                insertLocation.setInt(1, locations[i].getId());
                insertLocation.setString(2, locations[i].getName());
                insertLocation.addBatch();
                graph.addVertex(locations[i].getName());
            }
            insertLocation.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertProblems(Problem[] problems) {
        try (PreparedStatement insertProblems = connection.prepareStatement(
                "INSERT INTO Problem VALUES(?, ?, ?)"
        )) {
            for (int i = 0; i < problems.length; i++) {
                insertProblems.setInt(1, problems[i].getId());
                insertProblems.setInt(2, problems[i].getFromId());
                insertProblems.setInt(3, problems[i].getToId());
                insertProblems.addBatch();
            }
            insertProblems.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertRoutes(Route[] routes, Graph graph) {
        try (PreparedStatement insertRoutes = connection.prepareStatement(
                "INSERT INTO Route VALUES(?, ?, ?, ?)"
        )) {
            for (int i = 0; i < routes.length; i++) {
                insertRoutes.setInt(1, routes[i].getId());
                insertRoutes.setInt(2, routes[i].getFromId());
                insertRoutes.setInt(3, routes[i].getToId());
                insertRoutes.setInt(4, routes[i].getCost());
                insertRoutes.addBatch();
                graph.addEdge(routes[i].getFromId(), routes[i].getToId(), routes[i].getCost());
            }
            insertRoutes.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertSolutions(Solution[] solutions) {
        try (PreparedStatement insertLocation = connection.prepareStatement(
                "INSERT INTO Solution VALUES(?, ?)"
        )) {
            for (int i = 0; i < solutions.length; i++) {
                insertLocation.setInt(1, solutions[i].getProblemId());
                insertLocation.setInt(2, solutions[i].getCost());
                insertLocation.addBatch();
            }
            insertLocation.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}