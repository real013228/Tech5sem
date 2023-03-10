package org.real013228.bll.services.implementations;

import org.real013228.bll.exceptions.OwnerException;
import org.real013228.bll.services.abstractions.OwnCat;
import org.real013228.dal.DatabaseContext;
import org.real013228.dal.entity.Cat;
import org.real013228.dal.entity.Owner;

public class OwnCatImplementation implements OwnCat {
    DatabaseContext databaseContext;

    public OwnCatImplementation(DatabaseContext databaseContext) {
        this.databaseContext = databaseContext;
    }

    @Override
    public void ownerGetsCat(int ownerId, int catId) throws OwnerException {
        Owner owner = databaseContext.getOwnerHelper().getOwner(ownerId);
        Cat cat = databaseContext.getCatHelper().getCat(catId);
        if (owner.getCats().contains(cat)) {
            throw OwnerException.OwnerAlreadyGetCatWithId(catId);
        }

        databaseContext.ownCat(ownerId, catId);
    }
}
