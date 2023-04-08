package com.real013228.service;

import com.real013228.CatSpecification;
import com.real013228.Mapper;
import com.real013228.SearchCriteria;
import com.real013228.dto.CatDto;
import com.real013228.dto.FilterDto;
import com.real013228.entity.CatEntity;
import com.real013228.exceptions.CustomException;
import com.real013228.repository.CatRepository;
import com.real013228.repository.OwnerRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CatServiceImpl implements CatService {
    private final CatRepository catRepository;
    private final OwnerRepository ownerRepository;

    public CatServiceImpl(CatRepository catRepository, OwnerRepository ownerRepository) {
        this.catRepository = catRepository;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public List<CatDto> findAllCats() {
        List<CatDto> cats = new ArrayList<>();
        for(var cat : catRepository.findAll()) {
            cats.add(Mapper.asCatDto(cat));
        }

        return cats;
    }

    @Override
    public List<CatDto> findAllCatsWithColor(String color) throws CustomException {
        List<CatDto> cats = new ArrayList<>();
        for (CatEntity cat : catRepository.findAll().stream().filter(x -> (x.getColor().equals(color))).toList()) {
            cats.add(Mapper.asCatDto(cat));
        }
        if(cats.isEmpty()) {
            throw CustomException.InvalidColorException(color);
        }
        return cats;
    }

    @Override
    public List<CatDto> findAllCatsWithFilter(FilterDto filter) {
        CatSpecification spec = new CatSpecification(new SearchCriteria(filter.key(), filter.operation(), filter.value()));
        return catRepository.findAll(spec).stream().map(Mapper::asCatDto).toList();
    }

    @Override
    public CatDto findCatById(Long id) throws CustomException {
        if (id <= 0 || catRepository.findById(id).orElse(null) == null) {
            throw CustomException.InvalidIdException(id);
        }
        var cat = catRepository.findById(id).orElse(null);
        assert cat != null;
        return Mapper.asCatDto(cat);
    }

    @Override
    public void saveCat(CatDto cat) {
        catRepository.save(Mapper.asCatEntity(cat, catRepository));
    }

    @Override
    public void deleteCat(Long id) throws CustomException {
        if (id <= 0 || catRepository.findById(id).orElse(null) == null) {
            throw CustomException.InvalidIdException(id);
        }
        var cat = catRepository.findById(id).orElse(null);
        if (cat != null) {
            var owner = cat.getOwner();
            var friends = cat.getFriends();
            if (owner != null) {
                owner.getCats().remove(cat);
            }
            if (friends != null) {
                for(var friend : friends) {
                    friend.getFriends().remove(cat);
                    catRepository.save(friend);
                }
            }
            catRepository.deleteById(id);
        }
    }

    @Override
    public void makeFriends(Long firstCatId, Long secondCatId) throws CustomException {
        if (firstCatId <= 0 || catRepository.findById(firstCatId).orElse(null) == null) {
            throw CustomException.InvalidIdException(firstCatId);
        }
        if (secondCatId <= 0 || catRepository.findById(secondCatId).orElse(null) == null) {
            throw CustomException.InvalidIdException(secondCatId);
        }
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
