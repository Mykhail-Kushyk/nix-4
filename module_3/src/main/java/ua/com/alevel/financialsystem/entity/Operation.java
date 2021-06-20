package ua.com.alevel.financialsystem.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.*;

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
    private List<Category> categories = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "bill_id", nullable = false)
    private Bill bill;

    public Operation(Long id, List<Category> categories, Bill bill) {
        this.id = id;
        this.bill = bill;
        this.categories = categories;
        this.date = Instant.now();
        this.total = 0;
        for (Category category:categories) {
            this.total += category.getTotal();
        }
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
        this.total = 0;
        for (Category category:categories) {
            this.total += category.getTotal();
        }
    }
}