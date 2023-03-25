package org.real013228.lab3.bll;

import org.real013228.lab3.bll.services.implementations.*;
import org.real013228.lab3.dal.DatabaseContext;
import org.real013228.lab3.dal.helpers.CatHelper;
import org.real013228.lab3.dal.helpers.OwnerHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public CreateFriendshipImplementation createFriendshipImplementation() {
        return new CreateFriendshipImplementation(new DatabaseContext(new OwnerHelper(), new CatHelper()));
    }
    @Bean
    public CreateCatImplementation createCatImplementation() {
        return new CreateCatImplementation(new DatabaseContext(new OwnerHelper(), new CatHelper()));
    }
    @Bean
    public CreateOwnerImplementation createOwnerImplementation() {
        return new CreateOwnerImplementation(new DatabaseContext(new OwnerHelper(), new CatHelper()));
    }
    @Bean
    public OwnCatImplementation ownCatImplementation() {
        return new OwnCatImplementation(new DatabaseContext(new OwnerHelper(), new CatHelper()));
    }
    @Bean
    public RemoveFriendshipImplementation removeFriendshipImplementation() {
        return new RemoveFriendshipImplementation(new DatabaseContext(new OwnerHelper(), new CatHelper()));
    }
}
