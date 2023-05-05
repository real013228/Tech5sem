package com.real013228.controller;

import com.real013228.Mapper;
import com.real013228.dto.CatDto;
import com.real013228.dto.MakeFriendsDto;
import com.real013228.model.CatModel;
import com.real013228.service.CatService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cat")
public class CatController {
    CatService catService;
    public CatController(CatService catService) {
        this.catService = catService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCat(@RequestBody @Valid CatModel cat) {
        catService.saveCat(cat);
    }
    @GetMapping("/get/all")
    @ResponseStatus(HttpStatus.FOUND)
    public List<CatModel> getAllCats() {
        List<CatDto> res = catService.findAllCats();
        if (res != null)
            return res.stream().map(Mapper::asCatModel).toList();
        return new ArrayList<>();
    }
    @GetMapping("/get/by-id")
    @ResponseStatus(HttpStatus.OK)
    public CatModel findCatById(Long id) {
        CatDto catDto = catService.findCatById(id);
        return Mapper.asCatModel(catDto);
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
