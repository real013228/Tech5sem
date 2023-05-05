package com.real013228.service;

import com.real013228.dto.OwnerDto;
import com.real013228.model.OwnerModel;

import java.util.List;

public interface OwnerService {
    List<OwnerDto> findAllOwners();
    void saveOwner(OwnerModel owner);
    void ownCat(Long owner, Long cat);

    void deleteOwner(Long id);
}
