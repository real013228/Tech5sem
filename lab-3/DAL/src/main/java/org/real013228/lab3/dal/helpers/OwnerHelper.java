package org.real013228.lab3.dal.helpers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.real013228.lab3.dal.HibernateUtil;
import org.real013228.lab3.dal.entity.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OwnerHelper {
    private final SessionFactory sessionFactory;
    @Autowired
    public OwnerHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }
    public Owner getOwner(int id) {
        Session session = sessionFactory.getCurrentSession();
        Owner owner = session.get(Owner.class, id);
        session.close();
        return owner;
    }
    public void commitOwner(Owner owner) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        session.persist(owner);
        session.getTransaction().commit();
        session.close();
    }
}
