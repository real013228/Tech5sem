package org.real013228.pl.controllers;

import org.real013228.bll.exceptions.CatException;
import org.real013228.bll.services.abstractions.RemoveFriendship;
import org.real013228.pl.models.RemoveFriendshipModel;

public class RemoveFriendshipController {
    private final RemoveFriendship removeFriendshipService;

    public RemoveFriendshipController(RemoveFriendship removeFriendshipService) {
        this.removeFriendshipService = removeFriendshipService;
    }
    public void removeFriendship(RemoveFriendshipModel model) throws CatException {
        removeFriendshipService.removeFriendship(model.firstCatId(), model.secondCatId());
    }
}
