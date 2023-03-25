package org.real013228.lab3.pl.controllers;

import org.real013228.lab3.bll.exceptions.OwnerException;
import org.real013228.lab3.bll.services.implementations.OwnCatImplementation;
import org.real013228.lab3.pl.models.OwnCatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OwnCatController {
    private final OwnCatImplementation ownCatImplementationService;

    @Autowired
    public OwnCatController(OwnCatImplementation ownCatImplementationService) {
        this.ownCatImplementationService = ownCatImplementationService;
    }
    public void ownCat(OwnCatModel ownCatModel) throws OwnerException {
        ownCatImplementationService.ownerGetsCat(ownCatModel.ownerId(), ownCatModel.catId());
    }
}
