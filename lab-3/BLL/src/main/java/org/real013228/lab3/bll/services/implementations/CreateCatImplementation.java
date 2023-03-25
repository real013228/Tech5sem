package org.real013228.lab3.bll.services.implementations;

import org.real013228.lab3.bll.services.dto.CatDto;
import org.real013228.lab3.dal.DatabaseContext;
import org.real013228.lab3.dal.entity.Cat;

import java.util.ArrayList;
import java.util.Calendar;

public class CreateCatImplementation {
    private final DatabaseContext databaseContext;
    public CreateCatImplementation(DatabaseContext databaseContext) {
        this.databaseContext = databaseContext;
    }
//    @Override
    public CatDto createCat(String name, Calendar birthDate, String breed, String color, int ownerId) {
        Cat cat = new Cat();
        cat.setName(name);
        cat.setColor(color);
        cat.setBreed(breed);
        cat.setBirthDate(birthDate);
        if (ownerId != 0) {
            cat.setOwner(databaseContext.getOwnerHelper().getOwner(ownerId));
        }
        databaseContext.getCatHelper().commitCat(cat);
        return new CatDto(cat.getName(), cat.getBirthDate(), cat.getBreed(), cat.getColor(), ownerId, new ArrayList<>());
    }
}
