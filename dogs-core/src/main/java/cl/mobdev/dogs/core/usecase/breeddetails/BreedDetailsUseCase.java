package cl.mobdev.dogs.core.usecase.breeddetails;

import cl.mobdev.dogs.core.entity.Dog;
import cl.mobdev.dogs.core.usecase.exception.ApplicationException;
import cl.mobdev.dogs.core.usecase.exception.DataProviderException;

public class BreedDetailsUseCase {

    private GetBreedDetails getBreedDetails;

    public BreedDetailsUseCase(GetBreedDetails getBreedDetails){
        this.getBreedDetails = getBreedDetails;
    }

    public Dog breedDetailsUseCase(String breed) throws ApplicationException{
        try {
            return getBreedDetails.getBreedDetails(breed);
        }catch (DataProviderException e){
            throw new ApplicationException("xxx", "blabla");
        }
    }
}
