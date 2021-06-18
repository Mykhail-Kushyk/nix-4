package ua.com.alevel.financialsystem.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "operations")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operation_id")
    private Long id;

    private Instant date;

    private Integer total;

    @OneToMany(mappedBy = "operation")
    private Set<Category> categories = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "bill_id", nullable = false)
    private Bill bill;

    public Operation(Long id, Set<Category> categories, Bill bill) {
        this.id = id;
        this.bill = bill;
        this.categories = categories;
        this.date = Instant.now();
        this.total = 0;
        for (Category category:categories) {
            this.total += category.getTotal();
        }
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
        this.total = 0;
        for (Category category:categories) {
            this.total += category.getTotal();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(id, operation.id) &&
                Objects.equals(date, operation.date) &&
                Objects.equals(total, operation.total) &&
                Objects.equals(categories, operation.categories) &&
                Objects.equals(bill, operation.bill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, total, categories, bill);
    }
}