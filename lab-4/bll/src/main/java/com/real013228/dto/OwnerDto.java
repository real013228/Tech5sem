package com.real013228.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.util.List;

public record OwnerDto(@NotNull String name, @Past @Size(min = 10, max = 10) String birthDate, List<Long> cats, Long userAccountId){}
