package ua.com.alevel.financialsystem.entity;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.financialsystem.entity.categories.ExpenseType;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Expense extends Category {

    @Column(name = "expense_type")
    private String type;
}