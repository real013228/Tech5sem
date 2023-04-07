package com.real013228.controller;

import com.real013228.dto.CatDto;
import com.real013228.dto.MakeFriendsDto;
import com.real013228.service.CatService;
import org.springframework.web.bind.annotation.*;
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
        return catService.findCatById(id);
    }
    @PostMapping
    public void saveCat(@RequestBody CatDto cat) {
        catService.saveCat(cat);
    }
    @PutMapping("/make/friends")
    public void makeFriends(@RequestBody MakeFriendsDto cats) {
        catService.makeFriends(cats.firstCat(),cats. secondCat());
    }
    @DeleteMapping("/{id}")
    public void deleteCat(@PathVariable("id") Long id) {
        catService.deleteCat(id);
    }
}
