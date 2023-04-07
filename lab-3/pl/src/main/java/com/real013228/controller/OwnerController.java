package com.real013228.controller;

import com.real013228.dto.CatDto;
import com.real013228.dto.MakeOwnDto;
import com.real013228.dto.OwnerDto;
import com.real013228.service.OwnerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    private final OwnerService ownerService;
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }
    @GetMapping("/getAllOwners")
    public List<OwnerDto> findAllOwners() {
        return ownerService.findAllOwners();
    }
    @GetMapping("/{id}")
    public OwnerDto findOwnerById(@PathVariable("id") Long id) {
        return ownerService.findOwnerById(id);
    }
    @PostMapping
    public void saveOwner(@RequestBody OwnerDto owner) {
        ownerService.saveOwner(owner);
    }
    @PutMapping("/make/own")
    public void ownCat(@RequestBody MakeOwnDto pair) {
        ownerService.ownCat(pair.ownerId(), pair.catId());
    }
    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable("id") Long id) {
        ownerService.deleteOwner(id);
    }
}
