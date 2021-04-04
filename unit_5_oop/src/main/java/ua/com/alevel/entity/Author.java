package ua.com.alevel.entity;

import java.util.Objects;
import java.util.Set;

public class Author {
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

    private int id;
    private String firstName;
    private String lastName;
    private Set<Integer> booksIds;

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
}