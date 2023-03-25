package org.real013228.lab3.bll.services.abstractions;

import org.real013228.lab3.bll.services.dto.OwnerDto;

import java.util.Calendar;
import java.util.List;

public interface CreateOwner {
    OwnerDto createOwner(String name, Calendar birthDate, List<Integer> cats);
}
