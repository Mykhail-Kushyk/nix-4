package ua.com.alevel.jpa;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.com.alevel.jpa.dao.GroupDAO;
import ua.com.alevel.jpa.dao.LectureDAO;
import ua.com.alevel.jpa.dto.BestGroupByMedianDto;
import ua.com.alevel.jpa.entity.Lecture;

public class Main {

    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();

        try(SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession()) {
            LectureDAO dao = new LectureDAO(session);
            Lecture lecture = dao.getNearestLectureByStudent(1L);
            GroupDAO groupDAO = new GroupDAO(session);
            BestGroupByMedianDto dto = groupDAO.getBestGroupByMedianAndTeacher(1L);
            System.out.println(dto.getCourseName() + " " + dto.getId() + " " + dto.getName());
        }
    }
}