package ua.com.alevel;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class Factory {

    @SuppressWarnings("unchecked")
    public<T> T create(Properties props, Class<T> clazz) {
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            T object = constructor.newInstance();
            for (Field field : clazz.getFields()) {
                if (field.isAnnotationPresent(PropertyKey.class)) {
                    PropertyKey key = field.getAnnotation(PropertyKey.class);
                    if (key == null) continue;
                    String value = props.getProperty(key.value());
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
            return object;
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}