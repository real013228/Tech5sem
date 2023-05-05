package com.real013228.service;

import com.real013228.Mapper;
import com.real013228.dto.CatDto;
import com.real013228.entity.CatEntity;
import com.real013228.model.CatModel;
import com.real013228.repository.CatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatServiceImpl implements CatService {
    public CatServiceImpl(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    CatRepository catRepository;
    @Override
    public List<CatDto> findAllCats() {
        return catRepository.findAll().stream().map(Mapper::asCatDto).toList();
    }

    @Override
    public void saveCat(CatModel cat) {
        CatEntity catEntity = CatEntity.builder()
                .breed(cat.breed())
                .name(cat.name())
                .owner(cat.owner())
                .birthDate(cat.birthDate())
                .color(cat.color())
                .build();
        catRepository.save(catEntity);
    }

    @Override
    public void deleteCat(Long id) {
        catRepository.deleteById(id);
    }

    @Override
    public CatDto findCatById(Long id) {
        return Mapper.asCatDto(catRepository.findById(id).orElse(null));
    }

    @Override
    public void makeFriends(Long firstCatId, Long secondCatId) {
        var firstCat = catRepository.findById(firstCatId).orElse(null);
        var secondCat = catRepository.findById(secondCatId).orElse(null);
        var firstCatFriends = firstCat.getFriends();
        firstCatFriends.add(secondCat);

        var secondCatFriends = secondCat.getFriends();
        secondCatFriends.add(firstCat);

        firstCat.setFriends(firstCatFriends);
        secondCat.setFriends(secondCatFriends);
        catRepository.save(firstCat);
        catRepository.save(secondCat);
    }

}
