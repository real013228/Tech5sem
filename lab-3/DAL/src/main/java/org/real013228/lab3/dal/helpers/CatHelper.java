package org.real013228.lab3.dal.helpers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.real013228.lab3.dal.HibernateUtil;
import org.real013228.lab3.dal.entity.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CatHelper {
    private final SessionFactory sessionFactory;
    @Autowired
    public CatHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }
    public Cat getCat(int id) {

        Session session = sessionFactory.getCurrentSession();
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
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        session.persist(cat);
        session.getTransaction().commit();
        session.close();
    }
}
