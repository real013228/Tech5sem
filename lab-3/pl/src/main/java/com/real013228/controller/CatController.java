package com.real013228.controller;

import com.real013228.dto.CatDto;
import com.real013228.dto.FilterDto;
import com.real013228.dto.MakeFriendsDto;
import com.real013228.exceptions.CustomException;
import com.real013228.service.CatService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
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
        return catService.findCatById(id);
    }
    @GetMapping("/color/{colorName}")
    public List<CatDto> findAllCatsWithColor(@PathVariable("colorName") String colorName) {
        return catService.findAllCatsWithColor(colorName);
    }
    @GetMapping("/filter")
    public List<CatDto> findAllCatsWithFilter(@RequestBody FilterDto filter) {
        return catService.findAllCatsWithFilter(filter);
    }
    @PostMapping
    public void saveCat(@RequestBody @Valid CatDto cat) {
        catService.saveCat(cat);
    }
    @PutMapping("/make/friends")
    public void makeFriends(@RequestBody @Valid MakeFriendsDto cats) {
        catService.makeFriends(cats.firstCat(),cats. secondCat());
    }
    @DeleteMapping("/{id}")
    public void deleteCat(@PathVariable("id") @Min(1) Long id) {
        catService.deleteCat(id);
    }
}
