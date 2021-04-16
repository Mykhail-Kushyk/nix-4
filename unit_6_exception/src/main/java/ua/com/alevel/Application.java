package ua.com.alevel;

import ua.com.alevel.date.Date;
import ua.com.alevel.date.TimeOption;
import ua.com.alevel.format.DateFormat;
import ua.com.alevel.format.DateFormatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {

    private final Calendar calendar = new Calendar();

    public void run() {
        showMainMenu();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String option = "";
        try {
            option = reader.readLine();
            if (option.equals("0") || option.equals("1") || option.equals("2")) {
                switch (option) {
                    case "0": System.exit(0);
                    case "1": {
                        showCountDifference(reader); break;
                    }
                    case "2": {
                        showAddOrSubtractToDate(reader);
                    }
                }
            } else {
                System.out.println("Invalid option!");
                run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        run();
    }

    private void showMainMenu() {
        System.out.println("0. Exit");
        System.out.println("1. Count difference between two dates");
        System.out.println("2. Add or subtract to date smth");
        System.out.print("Choose option: ");
    }

    private void showAddOrSubtractToDate(BufferedReader reader) throws IOException {
        DateFormatter formatter = new DateFormatter(DateFormat.DEFAULT);
        setFormat(formatter, reader);
        Date date = scanDate(reader, formatter);
        showOptionsForManipulatingDates();
        String option = reader.readLine();
        switch (option) {
            case "1": {
                TimeOption timeOption = scanTimeOption(reader);
                date.add(scanNumberForManipulating(reader, timeOption), timeOption); break;
            }
            case "2": {
                TimeOption timeOption = scanTimeOption(reader);
                date.subtract(scanNumberForManipulating(reader, timeOption), timeOption);
            }
        }
        System.out.println("Result: " + date.getFormattedDate());
    }

    private void showOptionsForManipulatingDates() {
        System.out.println("1. Add");
        System.out.println("2. Subtract");
        System.out.print("Choose option: ");
    }

    private void showCountDifference(BufferedReader reader) throws IOException {
        DateFormatter formatter = new DateFormatter(DateFormat.DEFAULT);
        setFormat(formatter, reader);
        Date firstDate = scanDate(reader, formatter);
        Date secondDate = scanDate(reader, formatter);
        TimeOption timeOption = scanTimeOption(reader);
        long difference = calendar
                .getDifferenceIn(TimeOption.getOptionByNumber(timeOption.getNumber()), firstDate, secondDate);
        System.out.println("Difference: " + difference);
    }

    private Date scanDate(BufferedReader reader, DateFormatter formatter) throws IOException {
        System.out.println("Format: " + formatter.getFormat().getFormat());
        System.out.print("Input date please or input 1 to change format: ");
        boolean isOkay = false;
        Date date = new Date();
        while (!isOkay) {
            try {
                String input = reader.readLine();
                if (input.equals("1")) {
                    setFormat(formatter, reader);
                    date = scanDate(reader, formatter);
                } else {
                    date = new Date(input, formatter);
                }
                isOkay = true;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Format: " + formatter.getFormat().getFormat());
                System.out.print("Input date again please: ");
            }
        }
        return date;
    }

    private TimeOption scanTimeOption(BufferedReader reader) throws IOException {
        showTimeOptions();
        System.out.print("Choose time option: ");
        return TimeOption.getOptionByNumber(Integer.parseInt(reader.readLine()));
    }

    private void showTimeOptions() {
        for (TimeOption option: TimeOption.values()) {
            System.out.println(option.getNumber() + ". " + option);
        }
    }

    private long scanNumberForManipulating(BufferedReader reader, TimeOption option) throws IOException {
        System.out.print("Input " + option.toString().toLowerCase() + ": ");
        long number = Long.parseLong(reader.readLine());
        return number;
    }

    private void showFormats() {
        for (DateFormat format: DateFormat.values()) {
            System.out.println(format.getNumber() + ". " + format.getFormat());
        }
        System.out.print("Choose format: ");
    }

    private void setFormat(DateFormatter formatter, BufferedReader reader) throws IOException {
        showFormats();
        String option = reader.readLine();
        formatter.setFormat(DateFormat.getFormatByNumber(Integer.parseInt(option)));
    }
}