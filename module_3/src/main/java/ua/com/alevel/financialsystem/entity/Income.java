package ua.com.alevel.financialsystem.entity;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.financialsystem.entity.categories.ExpenseType;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Income extends Category {


    @Column(name = "income_type")
    private String incomeType;
}