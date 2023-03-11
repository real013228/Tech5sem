package org.real013228.pl.controllers;

import org.real013228.bll.exceptions.OwnerException;
import org.real013228.bll.services.implementations.OwnCatImplementation;
import org.real013228.pl.models.OwnCatModel;

public class OwnCatController {
    private final OwnCatImplementation ownCatImplementationService;

    public OwnCatController(OwnCatImplementation ownCatImplementationService) {
        this.ownCatImplementationService = ownCatImplementationService;
    }
    public void ownCat(OwnCatModel ownCatModel) throws OwnerException {
        ownCatImplementationService.ownerGetsCat(ownCatModel.ownerId(), ownCatModel.catId());
    }
}
