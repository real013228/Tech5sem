package com.real013228.controller;

import com.real013228.dto.CatDto;
import com.real013228.dto.FilterDto;
import com.real013228.dto.MakeFriendsDto;
import com.real013228.exceptions.CustomException;
import com.real013228.service.CatService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.service.annotation.PutExchange;

import java.util.List;

@RestController
@RequestMapping("/cat")
public class CatController {
    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }
    @GetMapping("/getAllCats")
    public List<CatDto> findAllCats() {
        return catService.findAllCats();
    }
    @GetMapping("/{id}")
    public CatDto findCatById(@PathVariable("id") Long id) {
        try {
            return catService.findCatById(id);
        } catch (CustomException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
    @GetMapping("/color/{colorName}")
    public List<CatDto> findAllCatsWithColor(@PathVariable("colorName") String colorName) {
        try {
            return catService.findAllCatsWithColor(colorName);
        } catch (CustomException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
    @GetMapping("/filter")
    public List<CatDto> findAllCatsWithFilter(@RequestBody FilterDto filter) {
        return catService.findAllCatsWithFilter(filter);
    }
    @PostMapping
    public void saveCat(@RequestBody CatDto cat) {
        catService.saveCat(cat);
    }
    @PutMapping("/make/friends")
    public void makeFriends(@RequestBody MakeFriendsDto cats) {
        try {
            catService.makeFriends(cats.firstCat(),cats. secondCat());
        } catch (CustomException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
    @DeleteMapping("/{id}")
    public void deleteCat(@PathVariable("id") Long id) {
        try {
            catService.deleteCat(id);
        } catch (CustomException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
}
