package ua.com.alevel.ionio.entity;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvCustomBindByPosition;
import ua.com.alevel.ionio.dao.converter.SetConverter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Book {

    @CsvBindByPosition(position = 0)
    private int id;
    @CsvBindByPosition(position = 1)
    private String name;
    @CsvCustomBindByPosition(position = 2, converter = SetConverter.class)
    Set<Integer> authorsIds;

    @Override
    public String toString() {
        return "id = " + id +
                ", name = " + name +
                ", authorsIds = " + authorsIds;
    }

    public Book() {
        this.id = 0;
        this.name = "default name";
        this.authorsIds = new HashSet<>();
    }

    public Book(int id, String name, Set<Integer> authorsIds) {
        this.id = id;
        this.name = name;
        this.authorsIds = authorsIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && Objects.equals(name, book.name) && Objects.equals(authorsIds, book.authorsIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, authorsIds);
    }

    public Set<Integer> getAuthorsIds() {
        return authorsIds;
    }

    public void setAuthorsIds(Set<Integer> authorsIds) {
        this.authorsIds = authorsIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}