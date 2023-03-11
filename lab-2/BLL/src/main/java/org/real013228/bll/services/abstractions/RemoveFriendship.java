package org.real013228.bll.services.abstractions;

import org.real013228.bll.exceptions.CatException;

public interface RemoveFriendship {
    boolean removeFriendship(int firstCatId, int secondCatId) throws CatException;
}
