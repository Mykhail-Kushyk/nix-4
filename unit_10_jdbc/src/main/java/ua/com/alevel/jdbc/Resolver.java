package ua.com.alevel.jdbc;

import ua.com.alevel.jdbc.entity.Solution;
import ua.com.alevel.jdbc.init.Inserter;
import ua.com.alevel.module.third.Graph;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Resolver {

    private Connection connection;

    public Resolver(Connection connection) {
        this.connection = connection;
    }

    public void resolveAllProblems(Graph graph) {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT p.id, p.from_id, p.to_id FROM Problem p " +
                    "LEFT JOIN Solution s ON s.problem_id = NULL");
            List<Solution> solutions = new ArrayList<>();

            while (resultSet.next()) {
                solutions.add(
                        new Solution(
                                resultSet.getInt("id"),
                                resultSet.getInt("from_id"),
                                resultSet.getInt("to_id"),
                                graph.findShortestPathBetweenCities(resultSet.getInt("from_id"),
                                        resultSet.getInt("to_id"))
                                ));
            }

            Inserter inserter = new Inserter(connection);
            Solution[] solves = new Solution[solutions.size()];
            for (int i = 0; i < solves.length; i++) {
                solves[i] = solutions.get(i);
            }
            inserter.insertSolutions(solves);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
                throw new RuntimeException(e);
            } catch (SQLException sqlException) {
                throw new RuntimeException(sqlException);
            }
        }
    }
}
