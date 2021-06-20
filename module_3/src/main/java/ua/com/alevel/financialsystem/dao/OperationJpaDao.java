package ua.com.alevel.financialsystem.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.financialsystem.entity.Operation;
import ua.com.alevel.financialsystem.entity.User;

public class OperationJpaDao {

    private Session session;
    private Logger logger = LoggerFactory.getLogger(OperationJpaDao.class);

    public OperationJpaDao(Session session) {
        this.session = session;
    }

    public void createOperation(Operation operation, Long userId) {
        logger.info("Creating operation, id - " + operation.getId() +
                ", user id - " + userId);

        Transaction transaction = session.beginTransaction();
        try {
            User user = session.get(User.class, userId);
            if (!user.getBills().contains(operation.getBill())) {
                throw new RuntimeException();
            }
            session.save(operation);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }
}