package org.real013228.bll.services.abstractions;

import org.real013228.bll.exceptions.CatException;
import org.real013228.dal.entity.Cat;

public interface CreateFriendship {
    boolean createFriendShip(int firstCat, int secondCat) throws CatException;
}
