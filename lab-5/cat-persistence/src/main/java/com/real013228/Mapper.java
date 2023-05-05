package com.real013228;

import com.real013228.dto.CatDto;
import com.real013228.entity.CatEntity;
import com.real013228.model.CatModel;

public class Mapper {
    public static CatModel asCatModel(CatDto catDto) {
        if (catDto == null)
            return null;
        return new CatModel(catDto.name(), catDto.birthDate(), catDto.breed(), catDto.color(), catDto.owner(), catDto.friends());
    }
    public static CatDto asCatDto(CatEntity catEntity) {
        if (catEntity == null)
            return null;
        return new CatDto(catEntity.getName(), catEntity.getBirthDate(), catEntity.getBreed(), catEntity.getColor(), catEntity.getOwner(), catEntity.getFriends().stream().map(CatEntity::getId).toList());
    }
}
