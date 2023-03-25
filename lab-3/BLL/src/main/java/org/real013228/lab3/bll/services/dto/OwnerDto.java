package org.real013228.lab3.bll.services.dto;

import java.util.Calendar;
import java.util.List;

public record OwnerDto(String name, Calendar birthDate, List<Integer> cats) {}
