package org.real013228.pl.controllers;

import org.real013228.bll.services.abstractions.CreateOwner;
import org.real013228.pl.models.CreateOwnerModel;

public class CreateOwnerController {
    private final CreateOwner createOwnerService;

    public CreateOwnerController(CreateOwner createOwnerService) {
        this.createOwnerService = createOwnerService;
    }
    public void createOwner(CreateOwnerModel owner) {
        createOwnerService.createOwner(owner.name(), owner.birthDate(), owner.cats());
    }
}
