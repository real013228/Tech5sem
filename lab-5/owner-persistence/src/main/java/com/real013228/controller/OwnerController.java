package com.real013228.controller;

import com.real013228.dto.OwnerDto;
import com.real013228.model.MakeOwnModel;
import com.real013228.model.OwnerModel;
import com.real013228.service.OwnerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/owner")
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public List<OwnerDto> findAllOwners()   {
        return ownerService.findAllOwners();
    }

    @PostMapping
    public void saveOwner(@RequestBody @Valid OwnerModel owner) {
        ownerService.saveOwner(owner);

    }
    @PutMapping("/make/own")
    public void ownCat(@RequestBody @Valid MakeOwnModel pair) {
        ownerService.ownCat(pair.ownerId(), pair.catId());
    }
    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable("id") Long id) {
        ownerService.deleteOwner(id);
    }
}
