package org.real013228.lab3.pl.controllers;

import org.real013228.lab3.bll.exceptions.CatException;
import org.real013228.lab3.bll.services.implementations.CreateFriendshipImplementation;
import org.real013228.lab3.pl.models.CreateFriendshipModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateFriendshipController {
    private final CreateFriendshipImplementation createFriendshipService;

    @Autowired
    public CreateFriendshipController(CreateFriendshipImplementation createFriendshipService) {
        this.createFriendshipService = createFriendshipService;
    }
    public void createFriendship(CreateFriendshipModel createFriendshipModel) throws CatException {
        createFriendshipService.createFriendShip(createFriendshipModel.firstCatId(), createFriendshipModel.secondCatId());
    }
}
