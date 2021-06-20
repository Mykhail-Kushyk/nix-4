package ua.com.alevel.jpa.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedNativeQueries(
        {
                @NamedNativeQuery(
                        name = "findByMedian",
                        query = "SELECT g.group_id FROM groups g\n" +
                                "INNER \n" +
                                "JOIN teachergroup tg ON tg.group_id = g.group_id\n" +
                                "\tJOIN students s ON s.group_id = g.group_id\n" +
                                "\tJOIN grades ON grades.student_id = s.student_id \n" +
                                "\tWHERE teacher_id = :teacher AND grades.type = 'FINAL_EXAM' \n" +
                                "\tGROUP BY g.group_id\n" +
                                "\tORDER BY percentile_cont(0.5) WITHIN GROUP(ORDER BY grades.rating) desc limit 1"
                )
        }
)

@Getter
@Entity
@Table(name = "Groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "group")
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "group")
    private List<TeacherGroup> teacherGroups = new ArrayList<>();
}