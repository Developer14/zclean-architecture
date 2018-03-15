package cl.mobdev.dogs.core.usecase.breeddetails;

import cl.mobdev.dogs.core.entity.Dog;
import cl.mobdev.dogs.core.usecase.exception.DataProviderException;

public interface GetBreedDetails {

    Dog getBreedDetails(String breed) throws DataProviderException;
}
