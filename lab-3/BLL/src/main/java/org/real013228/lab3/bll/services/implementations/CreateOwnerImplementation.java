package org.real013228.lab3.bll.services.implementations;

import org.real013228.lab3.bll.services.dto.OwnerDto;
import org.real013228.lab3.bll.services.abstractions.CreateOwner;
import org.real013228.lab3.dal.DatabaseContext;
import org.real013228.lab3.dal.entity.Cat;
import org.real013228.lab3.dal.entity.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
public class CreateOwnerImplementation {
    private DatabaseContext databaseContext;

    public CreateOwnerImplementation(DatabaseContext databaseContext) {
        this.databaseContext = databaseContext;
    }

//    @Override
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
