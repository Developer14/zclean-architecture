package cl.mobdev.dogs.core.usecase;

import cl.mobdev.dogs.core.entity.Dog;
import cl.mobdev.dogs.core.usecase.breeddetails.BreedDetailsUseCase;
import cl.mobdev.dogs.core.usecase.breeddetails.GetBreedDetails;
import cl.mobdev.dogs.core.usecase.exception.ApplicationException;
import cl.mobdev.dogs.core.usecase.exception.DataProviderException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class BreedDetailsUseCaseTest {

    @Mock
    private GetBreedDetails getBreedDetails;
    @InjectMocks
    private BreedDetailsUseCase breedDetailsUseCase = new BreedDetailsUseCase(getBreedDetails);

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() throws Exception{
        Dog dog = new Dog();
        Mockito.when(getBreedDetails.getBreedDetails("Pug")).thenReturn(dog);

        Dog ndog = breedDetailsUseCase.breedDetailsUseCase("Pug");

        Assert.assertNotNull(ndog);
    }

    @Test(expected = ApplicationException.class)
    public void testException() throws Exception{

        Dog dog = new Dog();
        Mockito.when(getBreedDetails.getBreedDetails("Pug")).thenThrow(new DataProviderException("", null));
        Dog ndog = breedDetailsUseCase.breedDetailsUseCase("Pug");

    }
}
