package ua.com.alevel.financialsystem.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "category_id")
    private Long categoryId;

    private Integer total;

    @ManyToOne()
    @JoinColumn(name = "operation_id")
    private Operation operation;
}