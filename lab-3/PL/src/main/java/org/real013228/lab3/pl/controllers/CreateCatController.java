package org.real013228.lab3.pl.controllers;

import org.real013228.lab3.bll.services.implementations.CreateCatImplementation;
import org.real013228.lab3.pl.models.CreateCatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateCatController {
    private final CreateCatImplementation createCatService;
    @Autowired
    public CreateCatController(CreateCatImplementation createCatService) {
        this.createCatService = createCatService;
    }
    @GetMapping("/create/cat")
    public void createCat(CreateCatModel cat) {
        createCatService.createCat(cat.name(), cat.birthDate(), cat.breed(), cat.color(), cat.ownerId());
    }
}
