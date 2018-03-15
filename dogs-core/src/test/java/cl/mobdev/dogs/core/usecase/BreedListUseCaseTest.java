package cl.mobdev.dogs.core.usecase;

import cl.mobdev.dogs.core.usecase.breedlist.BreedListUseCase;
import cl.mobdev.dogs.core.usecase.breedlist.GetBreedList;
import cl.mobdev.dogs.core.usecase.exception.ApplicationException;
import cl.mobdev.dogs.core.usecase.exception.DataProviderException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class BreedListUseCaseTest {

    @Mock
    private GetBreedList getBreedList;

    @InjectMocks
    private BreedListUseCase breedListUseCase = new BreedListUseCase(getBreedList);

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() throws Exception{
        Mockito.when(getBreedList.getBreedList()).thenReturn(new ArrayList<String>());

        List<String> list = breedListUseCase.getBreedList();

        Assert.assertNotNull(list);
    }

    @Test(expected = ApplicationException.class)
    public void testException() throws Exception{

        Mockito.when(getBreedList.getBreedList()).thenThrow(new DataProviderException("", null));
        breedListUseCase.getBreedList();

    }
}
