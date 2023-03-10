package org.real013228.dal;

import org.hibernate.Session;
import org.real013228.dal.entity.Cat;
import org.real013228.dal.entity.Owner;
import org.real013228.dal.helpers.CatHelper;
import org.real013228.dal.helpers.OwnerHelper;
import org.real013228.dal.model.Color;

import java.util.*;

public class Main {
    public static void main(String[] args) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.getTransaction().begin();
//        Owner owner = new Owner();
//        owner.setName("Kirill");
//        owner.setBirthDate(new GregorianCalendar());
        CatHelper catHelper = new CatHelper();
        OwnerHelper ownerHelper = new OwnerHelper();
        DatabaseContext db = new DatabaseContext(ownerHelper, catHelper);
        Cat cat = new Cat();
        cat.setBirthDate(new GregorianCalendar());
        cat.setName("sylgy ete");
        cat.setColor(Color.RED);
        cat.setBreed("artur petrovich");
        db.getCatHelper().commitCat(cat);
//        cat.setOwner(owner);
//
//        Cat cat1 = new Cat();
//        cat1.setBirthDate(new GregorianCalendar());
//        cat1.setName("Black");
//        cat1.setColor(Color.BLUE);
//        cat1.setBreed("nate higgers");
//        cat1.setOwner(owner);
//
//        owner.setCats(List.of(cat, cat1));
//        session.persist(owner);
//        session.persist(cat);
//        session.getTransaction().commit();
//        session.close();
    }
}