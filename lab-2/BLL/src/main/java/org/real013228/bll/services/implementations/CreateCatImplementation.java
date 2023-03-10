package org.real013228.bll.services.implementations;

import org.real013228.bll.services.abstractions.CreateCat;
import org.real013228.bll.services.dto.CatDto;
import org.real013228.bll.services.dto.OwnerDto;
import org.real013228.dal.DatabaseContext;
import org.real013228.dal.entity.Cat;
import org.real013228.dal.entity.Owner;
import org.real013228.dal.model.Color;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateCatImplementation implements CreateCat {
    DatabaseContext databaseContext;
    public CreateCatImplementation(DatabaseContext databaseContext) {
        this.databaseContext = databaseContext;
    }
    @Override
    public CatDto createCat(String name, Calendar birthDate, String breed, Color color, int ownerId, List<Integer> friendsId) {
        Cat cat = new Cat();
        cat.setName(name);
        cat.setColor(color);
        cat.setBreed(breed);
        cat.setBirthDate(birthDate);
        if (ownerId != 0) {
            cat.setOwner(databaseContext.getOwnerHelper().getOwner(ownerId));
        }
        if (friendsId != null) {
            List<Cat> friends = new ArrayList<>();
            for(int catId : friendsId) {
                friends.add(databaseContext.getCatHelper().getCat(catId));
            }
            cat.setFriends(friends);
        }
        databaseContext.getCatHelper().commitCat(cat);
        return new CatDto(cat.getName(), cat.getBirthDate(), cat.getBreed(), cat.getColor(), ownerId, friendsId);
    }
}
