package org.real013228.bll.services.abstractions;

import org.real013228.bll.services.dto.CatDto;
import org.real013228.bll.services.dto.OwnerDto;
import org.real013228.dal.entity.Owner;
import org.real013228.dal.model.Color;

import java.util.Calendar;
import java.util.List;

public interface CreateCat {
    CatDto createCat(String name, Calendar birthDate, String breed, Color color, int ownerId, List<Integer> friendsId);
}
