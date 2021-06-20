package ua.com.alevel.jpa.entity;

import javax.persistence.*;

@Entity
@Table(name = "Grades")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private Long id;

    @Column(nullable = false)
    private int rating;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Enumerated(EnumType.STRING)
    private GradeType type;
}