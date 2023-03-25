package org.real013228.lab3.bll.services.abstractions;

import org.real013228.lab3.bll.exceptions.OwnerException;

public interface OwnCat {
    void ownerGetsCat(int ownerId, int catId) throws OwnerException;
}
