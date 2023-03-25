package org.real013228.lab3.dal;

import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.real013228.lab3.dal.entity.Cat;
import org.real013228.lab3.dal.entity.Owner;
import org.real013228.lab3.dal.helpers.CatHelper;
import org.real013228.lab3.dal.helpers.OwnerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component("databaseContext")
@Getter
public class DatabaseContext {
    private final OwnerHelper ownerHelper;
    private final CatHelper catHelper;
    private final SessionFactory sessionFactory;
    @Autowired
    public DatabaseContext(OwnerHelper ownerHelper, CatHelper catHelper) {
        this.ownerHelper = ownerHelper;
        this.catHelper = catHelper;
        sessionFactory = HibernateUtil.getSessionFactory();
    }
    public void makeFriends(int firstCatId, int secondCatId) {
        Session session = sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        Cat firstCat = session.get(Cat.class, firstCatId);
        Cat secondCat = session.get(Cat.class, secondCatId);
        firstCat.getFriends().add(secondCat);
        secondCat.getFriends().add(firstCat);
        session.merge(firstCat);
        session.merge(secondCat);
        session.getTransaction().commit();
        session.close();
    }
    public void ownCat(int ownerId, int catId) {
        Session session = sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        Owner owner = session.get(Owner.class, ownerId);
        Cat cat = session.get(Cat.class, catId);
        owner.getCats().add(cat);
        cat.setOwner(owner);
        session.merge(cat);
        session.merge(owner);
        session.getTransaction().commit();
        session.close();
    }

    public void removeFriends(int firstCatId, int secondCatId) {
        Session session = sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        Cat firstCat = session.get(Cat.class, firstCatId);
        Cat secondCat = session.get(Cat.class, secondCatId);
        firstCat.getFriends().remove(secondCat);
        secondCat.getFriends().remove(firstCat);
        session.merge(firstCat);
        session.merge(secondCat);
        session.getTransaction().commit();
        session.close();
    }
    public int getFive() {
        return 5;
    }
}
