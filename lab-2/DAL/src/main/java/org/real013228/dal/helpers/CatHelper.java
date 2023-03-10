package org.real013228.dal.helpers;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.real013228.dal.HibernateUtil;
import org.real013228.dal.entity.Cat;
import org.real013228.dal.entity.Owner;

import java.util.List;

public class CatHelper {
    private SessionFactory sessionFactory;
    public CatHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }
    public Cat getCat(int id) {

        Session session = sessionFactory.openSession();
        Cat cat = session.get(Cat.class, id);
        session.close();
        return cat;
//        session.get(Cat.class, 1);
//        CriteriaBuilder cb = session.getCriteriaBuilder();
//        CriteriaQuery cq = cb.createQuery(Cat.class);
//        Root<Cat> root = cq.from(Cat.class);
//        cq.select(root);
//        Query query = session.createQuery(cq);
//        List<Cat> catList = query.getResultList();
//        session.close();
//        return catList;
    }
    public void commitCat(Cat cat) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.persist(cat);
        session.getTransaction().commit();
        session.close();
    }
}
