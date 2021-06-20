package ua.com.alevel.jpa.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ua.com.alevel.jpa.entity.Lecture;
import ua.com.alevel.jpa.entity.Student;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.Instant;

public class LectureDAO {

    private Session session;

    public LectureDAO(Session session) {
        this.session = session;
    }

    public Lecture getNearestLectureByStudent(Long studentId) {
        try {
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Lecture> criteriaQuery = builder.createQuery(Lecture.class);
            Root<Lecture> root = criteriaQuery.from(Lecture.class);
            Student student = session.get(Student.class, studentId);
            criteriaQuery.select(root).
                    where(builder.greaterThan(root.get("startDate"), Instant.now()));
            criteriaQuery.orderBy(builder.asc(root.get("startDate")));
            Query<Lecture> query = session.createQuery(criteriaQuery).setMaxResults(1);
            transaction.commit();
            return query.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}