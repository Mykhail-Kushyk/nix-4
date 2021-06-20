package ua.com.alevel.jpa.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Entity
@Table(name = "Lectures")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    private Long id;

    @Column(name = "start_date")
    private Instant startDate;

    private String topic;

    @ManyToOne()
    @JoinColumn(name = "group_id")
    private Group group;
}