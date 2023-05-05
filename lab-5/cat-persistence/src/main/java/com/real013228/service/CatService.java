package com.real013228.service;

import com.real013228.dto.CatDto;
import com.real013228.model.CatModel;

import java.util.List;

public interface CatService {
    List<CatDto> findAllCats();
    void saveCat(CatModel cat);
    void deleteCat(Long id);

    CatDto findCatById(Long id);
}
