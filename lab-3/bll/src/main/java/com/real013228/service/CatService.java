package com.real013228.service;

import com.real013228.dto.CatDto;
import com.real013228.dto.FilterDto;
import com.real013228.entity.CatEntity;
import com.real013228.exceptions.CustomException;

import java.util.List;
import java.util.Optional;

public interface CatService {
    List<CatDto> findAllCats();
    List<CatDto> findAllCatsWithColor(String color) throws CustomException;
    List<CatDto> findAllCatsWithFilter(FilterDto filter);
    CatDto findCatById(Long id) throws CustomException;
    void saveCat(CatDto cat);
    void deleteCat(Long id) throws CustomException;
    void makeFriends(Long firstCat, Long secondCat) throws CustomException;
}
