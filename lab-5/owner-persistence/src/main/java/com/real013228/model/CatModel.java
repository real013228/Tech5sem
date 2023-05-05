package com.real013228.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.util.Date;
import java.util.List;

public record CatModel(@NotNull(message = "Name cannot be null") String name, @Past Date birthDate, @NotBlank String breed, String color, @Min(1) Long owner, List<Long> friends) { }

