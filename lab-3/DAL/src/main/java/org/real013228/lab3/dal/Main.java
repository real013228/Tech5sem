package org.real013228.lab3.dal;

import org.real013228.lab3.dal.helpers.CatHelper;
import org.real013228.lab3.dal.helpers.OwnerHelper;

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
//        Cat cat = new Cat();
//        cat.setBirthDate(new GregorianCalendar());
//        cat.setName("zykov");
//        cat.setBreed("timur mongol");
//        cat.setColor("green");
//        db.getCatHelper().commitCat(cat);
//
//        Cat cat2 = new Cat();
//        cat2.setBirthDate(new GregorianCalendar());
//        cat2.setName("sylgy ete");
//        cat2.setBreed("artur petrovich");
//        cat2.setColor("red");
//        db.makeFriends(catHelper.getCat(3).getId(), 4);
//        Owner owner = new Owner();
//        owner.setName("emilia tan");
//        owner.setBirthDate(new GregorianCalendar());
//        Cat cat =db.getCatHelper().getCat(3);
//        owner.setCats(List.of(cat));
//        db.getOwnerHelper().commitOwner(owner);
        db.ownCat(4, 3);
//        db.getCatHelper().commitCat(cat2);
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