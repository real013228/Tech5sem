package org.real013228.lab3.pl.controllers;

import org.real013228.lab3.bll.exceptions.CatException;
import org.real013228.lab3.bll.services.abstractions.RemoveFriendship;
import org.real013228.lab3.bll.services.implementations.RemoveFriendshipImplementation;
import org.real013228.lab3.pl.models.RemoveFriendshipModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RemoveFriendshipController {
    private final RemoveFriendshipImplementation removeFriendshipService;

    @Autowired
    public RemoveFriendshipController(RemoveFriendshipImplementation removeFriendshipService) {
        this.removeFriendshipService = removeFriendshipService;
    }
    public void removeFriendship(RemoveFriendshipModel model) throws CatException {
        removeFriendshipService.removeFriendship(model.firstCatId(), model.secondCatId());
    }
}
