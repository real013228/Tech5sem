package org.real013228.bll.services.implementations;

import org.real013228.bll.services.abstractions.CreateOwner;
import org.real013228.bll.services.dto.OwnerDto;
import org.real013228.dal.DatabaseContext;
import org.real013228.dal.entity.Cat;
import org.real013228.dal.entity.Owner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateOwnerImplementation implements CreateOwner {
    DatabaseContext databaseContext;

    public CreateOwnerImplementation(DatabaseContext databaseContext) {
        this.databaseContext = databaseContext;
    }

    @Override
    public OwnerDto createOwner(String name, Calendar birthDate, List<Integer> catsId) {
        Owner owner = new Owner();
        owner.setBirthDate(birthDate);
        owner.setName(name);
        if (catsId != null) {
            List<Cat> cats = new ArrayList<>();
            for (var catId : catsId) {
                cats.add(databaseContext.getCatHelper().getCat(catId));
            }
            owner.setCats(cats);
        }
        databaseContext.getOwnerHelper().commitOwner(owner);
        return new OwnerDto(name, birthDate, catsId);
    }
}
