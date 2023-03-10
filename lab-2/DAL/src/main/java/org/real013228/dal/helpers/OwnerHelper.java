package org.real013228.dal.helpers;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.real013228.dal.HibernateUtil;
import org.real013228.dal.entity.Cat;
import org.real013228.dal.entity.Owner;

import java.util.List;

public class OwnerHelper {
    private SessionFactory sessionFactory;
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
