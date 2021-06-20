package ua.com.alevel.financialsystem.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Expense extends Category {

    @Column(name = "expense_type")
    private String type;
}