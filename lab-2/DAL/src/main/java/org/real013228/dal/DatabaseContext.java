package org.real013228.dal;

import lombok.Getter;
import org.real013228.dal.helpers.CatHelper;
import org.real013228.dal.helpers.OwnerHelper;

@Getter
public class DatabaseContext {
    public DatabaseContext(OwnerHelper ownerHelper, CatHelper catHelper) {
        this.ownerHelper = ownerHelper;
        this.catHelper = catHelper;
    }

    private OwnerHelper ownerHelper;
    private CatHelper catHelper;
}
