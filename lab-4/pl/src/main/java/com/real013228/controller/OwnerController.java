package com.real013228.controller;

import com.real013228.dto.MakeOwnDto;
import com.real013228.dto.OwnerDto;
import com.real013228.exceptions.CustomException;
import com.real013228.service.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @GetMapping("/getAllOwners")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<OwnerDto> findAllOwners() {
        return ownerService.findAllOwners();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public OwnerDto findOwnerById(@PathVariable("id") Long id) {
        try {
            return ownerService.findOwnerById(id);
        } catch (CustomException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void saveOwner(@RequestBody OwnerDto owner) {
        ownerService.saveOwner(owner);
    }
    @PutMapping("/make/own")
    public void ownCat(@RequestBody MakeOwnDto pair) {
        ownerService.ownCat(pair.ownerId(), pair.catId());
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteOwner(@PathVariable("id") Long id) {
        try {
            ownerService.deleteOwner(id);
        } catch (CustomException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
}
