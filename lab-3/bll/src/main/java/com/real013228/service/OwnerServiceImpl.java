package com.real013228.service;

import com.real013228.Mapper;
import com.real013228.dto.CatDto;
import com.real013228.dto.OwnerDto;
import com.real013228.entity.CatEntity;
import com.real013228.entity.OwnerEntity;
import com.real013228.exceptions.CustomException;
import com.real013228.repository.CatRepository;
import com.real013228.repository.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OwnerServiceImpl implements OwnerService{
    private final OwnerRepository ownerRepository;
    private final CatRepository catRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository, CatRepository catRepository) {
        this.ownerRepository = ownerRepository;
        this.catRepository = catRepository;
    }

    @Override
    public List<OwnerDto> findAllOwners() {
        List<OwnerDto> owners = new ArrayList<>();
        for(var owner : ownerRepository.findAll()) {
            owners.add(Mapper.asOwnerDto(owner));
        }

        return owners;
    }

    @Override
    public OwnerDto findOwnerById(Long id) throws CustomException {
        if (id <= 0 || ownerRepository.findById(id).orElse(null) == null) {
            throw CustomException.InvalidIdException(id);
        }
        var owner = ownerRepository.findById(id).orElse(null);
        assert owner != null;
        return Mapper.asOwnerDto(owner);
    }

    @Override
    public void saveOwner(OwnerDto owner) {
        ownerRepository.save(Mapper.asOwnerEntity(owner, catRepository));
    }

    @Override
    public void ownCat(Long ownerId, Long catId) {
        var owner = ownerRepository.findById(ownerId).orElse(null);
        assert owner != null;
        if (owner.getCats() != null) {
            var cats = owner.getCats();
            var cat = catRepository.findById(catId).orElse(null);
            assert cat != null;
            cats.add(cat);
        }
        ownerRepository.save(owner);
    }

    @Override
    public void deleteOwner(Long id) throws CustomException {
        if (id <= 0 || ownerRepository.findById(id).orElse(null) == null) {
            throw CustomException.InvalidIdException(id);
        }
        ownerRepository.deleteById(id);
    }
}
