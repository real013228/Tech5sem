package com.real013228.controller;

import com.real013228.dto.CatDto;
import com.real013228.dto.FilterDto;
import com.real013228.dto.MakeFriendsDto;
import com.real013228.service.CatService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cat")
public class CatController {
    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }
    @GetMapping("/getAllCats")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void saveCat(@RequestBody @Valid CatDto cat) {
        catService.saveCat(cat);
    }
    @PutMapping("/make/friends")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void makeFriends(@RequestBody @Valid MakeFriendsDto cats) {
        catService.makeFriends(cats.firstCat(),cats. secondCat());
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteCat(@PathVariable("id") @Min(1) Long id) {
        catService.deleteCat(id);
    }
}
