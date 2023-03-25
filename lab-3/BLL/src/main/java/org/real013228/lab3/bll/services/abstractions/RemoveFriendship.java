package org.real013228.lab3.bll.services.abstractions;

import org.real013228.lab3.bll.exceptions.CatException;

public interface RemoveFriendship {
    boolean removeFriendship(int firstCatId, int secondCatId) throws CatException;
}
