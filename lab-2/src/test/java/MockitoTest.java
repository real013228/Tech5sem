import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.real013228.bll.exceptions.CatException;
import org.real013228.bll.services.abstractions.CreateCat;
import org.real013228.bll.services.abstractions.CreateFriendship;
import org.real013228.bll.services.abstractions.RemoveFriendship;
import org.real013228.bll.services.dto.CatDto;
import org.real013228.bll.services.implementations.CreateCatImplementation;
import org.real013228.bll.services.implementations.CreateFriendshipImplementation;
import org.real013228.bll.services.implementations.RemoveFriendshipImplementation;
import org.real013228.dal.DatabaseContext;
import org.real013228.dal.entity.Cat;
import org.real013228.dal.helpers.CatHelper;
import org.real013228.dal.helpers.OwnerHelper;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MockitoTest {
    @Test
    public void makeCatsAndBothMakingFriendsTest() throws CatException {
        Cat firstCat = new Cat();
        firstCat.setId(1);
        Cat secondCat = new Cat();
        secondCat.setId(2);
        CreateFriendship createFriendshipService = Mockito.mock(CreateFriendshipImplementation.class);
        when(createFriendshipService.createFriendShip(firstCat.getId(), secondCat.getId())).thenReturn(true);
    }

    @Test
    public void removeFriendShipAndFriendshipWasRemoved() throws CatException {
        Cat thirdCat = new Cat();
        thirdCat.setId(3);
        Cat fourthCat = new Cat();
        fourthCat.setId(4);
        RemoveFriendship removeFriendshipService = Mockito.mock(RemoveFriendshipImplementation.class);
        when(removeFriendshipService.removeFriendship(thirdCat.getId(), fourthCat.getId())).thenReturn(true);
    }
}