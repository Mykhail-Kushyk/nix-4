package ua.com.alevel.ionio.entity;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;
import ua.com.alevel.ionio.dao.converter.SetConverter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Author {

    @CsvBindByPosition(position = 0)
    private int id;
    @CsvBindByPosition(position = 1)
    private String firstName;
    @CsvBindByPosition(position = 2)
    private String lastName;
    @CsvCustomBindByPosition(position = 3, converter = SetConverter.class, required = false)
    private Set<Integer> booksIds;

    public Author() {
        this.id = 0;
        this.firstName = "default name";
        this.lastName = "default last name";
        this.booksIds = new HashSet<>();
    }

    public Author(int id, String firstName, String lastName, Set<Integer> booksIds) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.booksIds = booksIds;
    }

    @Override
    public String toString() {
        return "id = " + id +
                ", firstName = " + firstName +
                ", lastName = " + lastName +
                ", booksIds = " + booksIds;
    }

    public Set<Integer> getBooksIds() {
        return booksIds;
    }

    public void setBooksIds(Set<Integer> booksIds) {
        this.booksIds = booksIds;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id && Objects.equals(firstName, author.firstName) && Objects.equals(lastName, author.lastName) && Objects.equals(booksIds, author.booksIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, booksIds);
    }
}