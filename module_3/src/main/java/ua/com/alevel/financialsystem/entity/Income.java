package ua.com.alevel.financialsystem.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Income extends Category {


    @Column(name = "income_type")
    private String incomeType;
}