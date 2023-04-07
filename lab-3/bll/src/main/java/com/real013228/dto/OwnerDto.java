package com.real013228.dto;

import com.real013228.entity.CatEntity;

import java.util.List;

public record OwnerDto(String name, String birthDate, List<Long> cats){}
