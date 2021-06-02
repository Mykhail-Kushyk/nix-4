package ua.com.alevel.csvparser;

import ua.com.alevel.csvparser.annotation.CsvName;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvMapper {

    @SuppressWarnings("unchecked")
    public<T> List<T> mapCsvToList(List<String[]> linesFromCsv, Class<T> type) {
        String[] headers = linesFromCsv.get(0);
        List<T> list = new ArrayList<>();
        for (int i = 1; i < linesFromCsv.size(); i++) {
            int length = linesFromCsv.get(i).length;
            Map<String, String> valuesByName = new HashMap<>();
            String[] lines = linesFromCsv.get(i);

            for (int j = 0; j < length; j++) {
                valuesByName.put(headers[j], lines[j]);
            }

            try {
                Constructor<T> constructor = type.getDeclaredConstructor();
                T object = constructor.newInstance();


                for (Field field : type.getDeclaredFields()) {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(CsvName.class)) {
                        CsvName key = field.getAnnotation(CsvName.class);
                        if (key == null) continue;
                        String value = valuesByName.get(key.name());
                        if (value == null) continue;
                        Class<?> fieldType = field.getType();
                        if (fieldType == String.class) {
                            field.set(object, value);
                        } else if (fieldType.isEnum()) {
                            field.set(object, Enum.valueOf((Class<Enum>) fieldType, value));
                        } else if (fieldType == int.class || fieldType == Integer.class) {
                            field.setInt(object, Integer.parseInt(value));
                        } else if (fieldType == long.class || fieldType == Long.class) {
                            field.setLong(object, Long.parseLong(value));
                        } else if (fieldType == double.class || fieldType == Double.class) {
                            field.setDouble(object, Double.parseDouble(value));
                        } else if (fieldType == boolean.class || fieldType == Boolean.class) {
                            field.setBoolean(object, Boolean.parseBoolean(value));
                        }
                    }
                }
                list.add(object);
            } catch (NoSuchMethodException | IllegalAccessException |
                    InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}