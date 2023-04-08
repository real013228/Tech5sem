package com.real013228.dto;

import jakarta.validation.constraints.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public record CatDto(@NotNull(message = "Name cannot be null") String name, @Past Date birthDate, @NotBlank String breed, String color, @Min(1) Long owner, List<Long> friends){}
