package com.real013228.dto;

import java.util.Calendar;
import java.util.List;

public record CatDto(String name, String birthDate, String breed, String color, Long owner, List<Long> friends){}
