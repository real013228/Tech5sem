package org.real013228.bll.services.dto;

import org.real013228.dal.model.Color;

import java.util.Calendar;
import java.util.List;

public record CatDto(String name, Calendar birthDate, String breed, Color color, int owner, List<Integer> friends){}
