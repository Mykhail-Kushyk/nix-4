package ua.com.alevel.jpa.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.com.alevel.jpa.dto.BestGroupByMedianDto;
import ua.com.alevel.jpa.entity.Group;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class GroupDAO {

    private Session session;

    public GroupDAO(Session session) {
        this.session = session;
    }

    public BestGroupByMedianDto getBestGroupByMedianAndTeacher(Long teacherId) {
        try {
            Transaction transaction = session.beginTransaction();
            TypedQuery query = session.getNamedQuery("findByMedian").setParameter("teacher", teacherId);
            Long bestGroupId = Long.parseLong(query.getSingleResult().toString());
            Group group = session.get(Group.class, bestGroupId);
            Query teacherGroup = session.createQuery(
                    "select tg.courseName from TeacherGroup tg where tg.id.teacherId = :teacher and tg.id.groupId = :group")
                    .setParameter("teacher", teacherId).setParameter("group", group.getId());
            BestGroupByMedianDto dto =
                    new BestGroupByMedianDto(group.getId(), group.getName(),
                            (String) teacherGroup.getSingleResult());
            transaction.commit();
            return dto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}