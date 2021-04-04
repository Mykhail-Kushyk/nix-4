package ua.com.alevel.entity;

import java.util.Objects;
import java.util.Set;

public class Book {

    private int id;
    private String name;
    Set<Integer> authorsIds;

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