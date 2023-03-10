package org.real013228.bll.services.dto;

import java.util.Calendar;
import java.util.List;

public record CatDto(String name, Calendar birthDate, String breed, String color, int owner, List<Integer> friends){}
