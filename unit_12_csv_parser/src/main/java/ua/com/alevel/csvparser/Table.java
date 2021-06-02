package ua.com.alevel.csvparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Table {

    private List<String> headers = new ArrayList<>();
    private List<String[]> records = new ArrayList<>();

    public Table(List<String[]> linesFromCsv) {
        String[] stringsHeaders = linesFromCsv.get(0);
        headers.addAll(Arrays.asList(stringsHeaders));
        for (int i = 1; i < linesFromCsv.size(); i++) {
            records.add(linesFromCsv.get(i));
        }
    }

    public List<String> getHeaders() {
        return headers;
    }

    public String get(int row, int column) {
        String[] record = records.get(--row);
        return record[--column];
    }

    public String get(int row, String headerName) {
        String[] record = records.get(--row);
        return record[getIndexByHeaderName(headerName)];
    }

    private int getIndexByHeaderName(String name) {
        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).equals(name)) {
                return i;
            }
        }
        return -1;
    }
}