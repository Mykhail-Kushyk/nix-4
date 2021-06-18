package ua.com.alevel.financialsystem.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private Long id;

    @Column(name = "current_amount")
    private Integer currentAmount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "bill")
    private Set<Operation> operations = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return Objects.equals(id, bill.id) &&
                Objects.equals(currentAmount, bill.currentAmount) &&
                Objects.equals(user, bill.user) &&
                Objects.equals(operations, bill.operations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currentAmount, user, operations);
    }
}