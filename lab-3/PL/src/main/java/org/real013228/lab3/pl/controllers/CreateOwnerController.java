package org.real013228.lab3.pl.controllers;

import org.real013228.lab3.bll.services.abstractions.CreateOwner;
import org.real013228.lab3.bll.services.implementations.CreateOwnerImplementation;
import org.real013228.lab3.pl.models.CreateOwnerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateOwnerController {
    private final CreateOwnerImplementation createOwnerService;

    @Autowired
    public CreateOwnerController(CreateOwnerImplementation createOwnerService) {
        this.createOwnerService = createOwnerService;
    }
    public void createOwner(CreateOwnerModel owner) {
        createOwnerService.createOwner(owner.name(), owner.birthDate(), owner.cats());
    }
}
