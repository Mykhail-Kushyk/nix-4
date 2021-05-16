package ua.com.alevel.ionio.dao.converter;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import java.util.HashSet;
import java.util.Set;

public class SetConverter extends AbstractBeanField {
    @Override
    protected Object convert(String string) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        if (string.isEmpty()) {
            return new HashSet<Integer>();
        }
        string = string.substring(1, string.length() - 1);
        String[] data = string.split(", ");
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < data.length; i++) {
            set.add(Integer.parseInt(data[i]));
        }
        return set;
    }
}