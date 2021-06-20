package ua.com.alevel.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TeacherGroupKey implements Serializable {

    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(name = "group_id")
    private Long groupId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherGroupKey that = (TeacherGroupKey) o;
        return Objects.equals(teacherId, that.teacherId) && Objects.equals(groupId, that.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, groupId);
    }
}