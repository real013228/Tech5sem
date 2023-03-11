package org.real013228.bll.services.implementations;

import org.real013228.bll.exceptions.CatException;
import org.real013228.bll.services.abstractions.RemoveFriendship;
import org.real013228.dal.DatabaseContext;
import org.real013228.dal.entity.Cat;

public class RemoveFriendshipImplementation implements RemoveFriendship {
    private DatabaseContext databaseContext;

    public RemoveFriendshipImplementation(DatabaseContext databaseContext) {
        this.databaseContext = databaseContext;
    }

    @Override
    public boolean removeFriendship(int firstCatId, int secondCatId) throws CatException {
        Cat firstCat = databaseContext.getCatHelper().getCat(firstCatId);
        Cat secondCat = databaseContext.getCatHelper().getCat(secondCatId);
        if (firstCat.getFriends().stream().filter(x -> x.getId() == secondCat.getId()).findFirst().orElse(null) != null) {
            throw CatException.catFriendAlreadyExistException(secondCat.getId());
        }
        if (secondCat.getFriends().stream().filter(x -> x.getId() == firstCat.getId()).findFirst().orElse(null) != null) {
            throw CatException.catFriendAlreadyExistException(firstCat.getId());
        }

        databaseContext.removeFriends(firstCatId, secondCatId);
        return true;
    }
}
