package org.real013228.bll.services.abstractions;

import org.real013228.bll.services.dto.CatDto;

import java.util.Calendar;
import java.util.List;

public interface CreateCat {
    CatDto createCat(String name, Calendar birthDate, String breed, String color, int ownerId);
}
