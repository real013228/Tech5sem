package org.real013228.pl.controllers;

import org.real013228.bll.services.abstractions.CreateCat;
import org.real013228.pl.models.CreateCatModel;

public class CreateCatController {
    private final CreateCat createCatService;
    public CreateCatController(CreateCat createCatService) {
        this.createCatService = createCatService;
    }
    public void createCat(CreateCatModel cat) {
        createCatService.createCat(cat.name(), cat.birthDate(), cat.breed(), cat.color(), cat.ownerId());
    }
}
