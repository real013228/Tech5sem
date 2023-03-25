package org.real013228.lab3.bll.services.implementations;

import org.real013228.lab3.bll.exceptions.OwnerException;
import org.real013228.lab3.bll.services.abstractions.OwnCat;
import org.real013228.lab3.dal.DatabaseContext;
import org.real013228.lab3.dal.entity.Cat;
import org.real013228.lab3.dal.entity.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class OwnCatImplementation {
    private final DatabaseContext databaseContext;

    public OwnCatImplementation(DatabaseContext databaseContext) {
        this.databaseContext = databaseContext;
    }

//    @Override
    public void ownerGetsCat(int ownerId, int catId) throws OwnerException {
        Owner owner = databaseContext.getOwnerHelper().getOwner(ownerId);
        Cat cat = databaseContext.getCatHelper().getCat(catId);
        if (owner.getCats().contains(cat)) {
            throw OwnerException.OwnerAlreadyGetCatWithId(catId);
        }

        databaseContext.ownCat(ownerId, catId);
    }
}
