package ua.com.alevel.jpa.entity;

import javax.persistence.*;

@Entity
public class TeacherGroup {

    @EmbeddedId
    TeacherGroupKey id;

    @ManyToOne
    @MapsId("teacherId")
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @MapsId("groupId")
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "course_name")
    private String courseName;
}