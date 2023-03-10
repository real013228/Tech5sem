package org.real013228.pl.controllers;

import org.real013228.bll.exceptions.CatException;
import org.real013228.bll.services.abstractions.CreateFriendship;
import org.real013228.pl.models.CreateFriendshipModel;

public class CreateFriendshipController {
    private final CreateFriendship createFriendshipService;

    public CreateFriendshipController(CreateFriendship createFriendshipService) {
        this.createFriendshipService = createFriendshipService;
    }
    public void createFriendship(CreateFriendshipModel createFriendshipModel) throws CatException {
        createFriendshipService.createFriendShip(createFriendshipModel.firstCatId(), createFriendshipModel.secondCatId());
    }
}
