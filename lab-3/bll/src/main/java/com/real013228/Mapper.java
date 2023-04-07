package com.real013228;

import com.real013228.dto.CatDto;
import com.real013228.dto.OwnerDto;
import com.real013228.entity.CatEntity;
import com.real013228.entity.OwnerEntity;
import com.real013228.repository.CatRepository;
import com.real013228.repository.OwnerRepository;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static CatDto asCatDto(CatEntity cat) {
        var ownerId = 0L;
        if(cat.getOwner() != null) {
            ownerId = cat.getOwner().getId();
        }
        CatDto catDto = new CatDto(cat.getBreed(), cat.getBirthDate(), cat.getBreed(), cat.getColor(), ownerId, cat.getFriends().stream().map(CatEntity::getId).toList());
        return catDto;
    }
    public static CatEntity asCatEntity(CatDto cat, CatRepository catRepository) {
        CatEntity catEntity = new CatEntity();
        catEntity.setBirthDate(cat.birthDate());
        catEntity.setName(cat.name());
        catEntity.setBreed(cat.breed());
        List<CatEntity> friends = new ArrayList<>();
        for(var friend : cat.friends()) {
            friends.add(catRepository.findById(friend).orElse(null));
        }
        catEntity.setFriends(friends);
        catEntity.setColor(cat.color());
        return catEntity;
    }
    public static OwnerDto asOwnerDto(OwnerEntity owner) {
        return new OwnerDto(owner.getName(), owner.getBirthDate(), owner.getCats().stream().map(CatEntity::getId).toList());
    }
    public static OwnerEntity asOwnerEntity(OwnerDto owner, CatRepository catRepository) {
        OwnerEntity ownerEntity = new OwnerEntity();
        ownerEntity.setBirthDate(owner.birthDate());
        ownerEntity.setName(owner.name());
        List<CatEntity> cats = new ArrayList<>();
        for (var friend : owner.cats()) {
            cats.add(catRepository.findById(friend).orElse(null));
        }
        ownerEntity.setCats(cats);
        return ownerEntity;
    }
}
