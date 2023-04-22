package com.real013228.service;

import com.real013228.dto.OwnerDto;
import com.real013228.exceptions.CustomException;

import java.util.List;

public interface OwnerService {
    List<OwnerDto> findAllOwners();
    OwnerDto findOwnerById(Long id) throws CustomException;
    void saveOwner(OwnerDto owner);
    void ownCat(Long ownerId, Long catId);
    void deleteOwner(Long id) throws CustomException;
}
