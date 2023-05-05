package com.real013228;

import com.real013228.dto.OwnerDto;
import com.real013228.entity.CatEntity;
import com.real013228.entity.OwnerEntity;
import com.real013228.model.CatModel;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static OwnerDto asOwnerDto(OwnerEntity ownerEntity) {
        List<CatEntity> catIds = ownerEntity.getCats();
        if (catIds == null)
            catIds = new ArrayList<>();
        return new OwnerDto(ownerEntity.getName(), ownerEntity.getBirthDate(), catIds.stream().map(x -> x.getId()).toList(), ownerEntity.getUserAccount());
    }

    public static CatEntity asCatEntity(CatModel res, Long catId, Long ownerId) {
        return CatEntity.builder()
                .id(catId)
                .name(res.name())
                .owner(ownerId)
                .color(res.color())
                .breed(res.breed())
                .birthDate(res.birthDate())
                .build();
    }
}
