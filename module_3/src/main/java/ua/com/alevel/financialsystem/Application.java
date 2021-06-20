package ua.com.alevel.financialsystem;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.financialsystem.dao.BillJpaDao;
import ua.com.alevel.financialsystem.dao.OperationJdbcDao;
import ua.com.alevel.financialsystem.dao.OperationJpaDao;
import ua.com.alevel.financialsystem.entity.Category;
import ua.com.alevel.financialsystem.entity.Expense;
import ua.com.alevel.financialsystem.entity.Income;
import ua.com.alevel.financialsystem.entity.Operation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Instant;
import java.util.*;

public class Application {

    private Logger logger = LoggerFactory.getLogger(Application.class);

    public void run() {
        Configuration configuration = new Configuration().configure();
        Properties properties = new Properties();
        properties.setProperty("url", "jdbc:postgresql://localhost:5432/module3");
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Enter user id: ");
            Long userId = Long.parseLong(reader.readLine());
            System.out.print("Username: ");
            properties.setProperty("username", reader.readLine());
            System.out.print("Password: ");
            properties.setProperty("password", reader.readLine());
            configuration.setProperty("hibernate.connection.username", properties.getProperty("username"));
            configuration.setProperty("hibernate.connection.password", properties.getProperty("password"));
            try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
                while (true) {
                    System.out.println("Menu: ");
                    System.out.println("0. Exit");
                    System.out.println("1. Create operation");
                    System.out.println("2. Create ordering for duration by dates");
                    System.out.print("Your choice: ");
                    int choice = Integer.parseInt(reader.readLine());
                    if (choice == 0) {
                        return;
                    } else if (choice == 1) {
                        try(Session session = sessionFactory.openSession()) {
                            OperationJpaDao jpaDao = new OperationJpaDao(session);
                            BillJpaDao billJpaDao = new BillJpaDao(session);
                            Operation operation = new Operation();
                            scanOperation(operation, reader, billJpaDao);
                            jpaDao.createOperation(operation, userId);
                        }
                    } else if (choice == 2) {
                        try(Connection connection = DriverManager.getConnection(
                                properties.getProperty("url"),
                                properties.getProperty("username"),
                                properties.getProperty("password"))) {
                            System.out.println("First date");
                            Instant toDate = scanDate(reader);
                            System.out.println("Second date");
                            Instant fromDate = scanDate(reader);
                            System.out.print("Bill id: ");
                            Long billId = Long.parseLong(reader.readLine());
                            OperationJdbcDao jdbcDao = new OperationJdbcDao(connection);
                            jdbcDao.getOperationsByDatesToCsv(toDate, fromDate, billId);
                        }
                    }

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Instant scanDate(BufferedReader reader) throws IOException {
        System.out.print("Input date (example:2020-06-12T14:00:00.00Z): ");
        return Instant.parse(reader.readLine());
    }

    private void scanOperation(Operation operation, BufferedReader reader, BillJpaDao dao) throws IOException {
        System.out.print("Date (example:2020-06-12T14:00:00.00Z): ");
        operation.setDate(Instant.parse(reader.readLine()));
        System.out.print("Bills id: ");
        Long id = Long.parseLong(reader.readLine());
        operation.setBill(dao.findById(id));
        System.out.print("How many categories: ");
        int total = Integer.parseInt(reader.readLine());
        System.out.println("1. Income");
        System.out.println("2. Expense");
        System.out.println("Choose type: ");
        int type = Integer.parseInt(reader.readLine());
        if (type == 1) {
            scanIncome(reader, operation, total);
        } else if(type == 2) {
            scanExpense(reader, operation, total);
        } else {
            throw new RuntimeException();
        }
    }

    private void scanIncome(BufferedReader reader, Operation operation, int total) throws IOException {

        List<Category> categories = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            Income income = new Income();
            income.setOperation(operation);
            System.out.print("Income category: ");
            income.setIncomeType(reader.readLine());
            System.out.print("Total: ");
            income.setTotal(Integer.parseInt(reader.readLine()));
            categories.add(income);
        }
        operation.setCategories(categories);
    }

    private void scanExpense(BufferedReader reader, Operation operation, int total) throws IOException {

        List<Category> categories = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            Expense expense = new Expense();
            expense.setOperation(operation);
            System.out.print("Expense category: ");
            expense.setType(reader.readLine());
            System.out.print("Total: ");
            expense.setTotal(Integer.parseInt(reader.readLine()));
            categories.add(expense);
        }
        operation.setCategories(categories);
    }
}