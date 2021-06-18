package ua.com.alevel.financialsystem.dao;

import org.slf4j.Logger;
import ua.com.alevel.financialsystem.entity.OperationJdbc;
import ua.com.alevel.financialsystem.util.CsvUtil;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;

public class OperationJdbcDao {

    private Connection connection;
    private Logger logger = LoggerFactory.getLogger(OperationJdbcDao.class);

    public OperationJdbcDao(Connection connection) {
        this.connection = connection;
    }

    public void getOperationsByDatesToCsv(Instant fromDate, Instant toDate, Long billId) {

        logger.info("Getting operations from " +
                fromDate.toString() + " to " + toDate.toString());
        String query = "SELECT o.operation_id, o.date, o.total, o.bill_id\n" +
                "FROM operations o\n" +
                "WHERE date >= ? AND o.bill_id = ? \n" +
                "GROUP BY o.operation_id\n" +
                "HAVING date <= ?";

        String income = "SELECT SUM(income.total) AS total_income\n" +
                "FROM operations o \n" +
                "right join income on o.operation_id = income.operation_id\n" +
                "WHERE date >= ? AND date <= ?\n" +
                "GROUP BY o.operation_id\n" +
                "HAVING o.bill_id = ?";

        String balance = "SELECT SUM(total) AS balance\n" +
                "FROM operations o \n" +
                "WHERE date >= ? AND date <= ?\n" +
                "GROUP BY o.operation_id\n" +
                "HAVING o.bill_id = ?";

        try(PreparedStatement statement = connection.prepareStatement(query);
        PreparedStatement incomeStatement = connection.prepareStatement(income);
        PreparedStatement balanceStatement = connection.prepareStatement(balance)
        ) {
            connection.setAutoCommit(false);
            statement.setTimestamp(1, Timestamp.from(fromDate));
            statement.setLong(2, billId);
            statement.setTimestamp(3, Timestamp.from(toDate));
            statement.execute();
            ResultSet rs = statement.getResultSet();

            List<OperationJdbc> operations = new ArrayList<>();
            while (rs.next()) {
                OperationJdbc operation = new OperationJdbc();
                operation.setId(rs.getLong("operation_id"));
                operation.setTotal(rs.getInt("total"));
                operation.setBillId(rs.getLong("bill_id"));
                operation.setDate(rs.getTimestamp("date").toInstant());
                operations.add(operation);
            }

            CsvUtil util = new CsvUtil();

            incomeStatement.setTimestamp(1, Timestamp.from(fromDate));
            incomeStatement.setTimestamp(2, Timestamp.from(toDate));
            incomeStatement.setLong(3, billId);
            incomeStatement.execute();
            rs = incomeStatement.getResultSet();
            rs.next();
            Integer totalIncome = rs.getInt("total_income");

            balanceStatement.setTimestamp(1, Timestamp.from(fromDate));
            balanceStatement.setTimestamp(2, Timestamp.from(toDate));
            balanceStatement.setLong(3, billId);
            balanceStatement.execute();
            rs = balanceStatement.getResultSet();
            rs.next();
            Integer totalBalance = rs.getInt("balance");

            util.createOrdering(operations, totalIncome, totalBalance);

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
                throw new RuntimeException(e);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}