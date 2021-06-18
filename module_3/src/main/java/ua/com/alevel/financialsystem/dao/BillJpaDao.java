package ua.com.alevel.financialsystem.dao;

import org.hibernate.Session;
import ua.com.alevel.financialsystem.entity.Bill;

public class BillJpaDao {

    private Session session;

    public BillJpaDao(Session session) {
        this.session = session;
    }

    public Bill findById(Long id) {
        return session.get(Bill.class, id);
    }
}
