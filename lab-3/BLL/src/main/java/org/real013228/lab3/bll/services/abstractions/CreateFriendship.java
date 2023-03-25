package org.real013228.lab3.bll.services.abstractions;

import org.real013228.lab3.bll.exceptions.CatException;


public interface CreateFriendship {
    boolean createFriendShip(int firstCat, int secondCat) throws CatException;
}
