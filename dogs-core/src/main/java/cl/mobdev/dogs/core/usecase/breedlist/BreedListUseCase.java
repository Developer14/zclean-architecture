package cl.mobdev.dogs.core.usecase.breedlist;

import cl.mobdev.dogs.core.usecase.exception.ApplicationException;
import cl.mobdev.dogs.core.usecase.exception.DataProviderException;

import java.util.List;

public class BreedListUseCase {

    private GetBreedList getBreedList;

    public BreedListUseCase(GetBreedList getBreedList){
        this.getBreedList = getBreedList;
    }

    public List<String> getBreedList() throws ApplicationException{
        try {
            return getBreedList.getBreedList();
        }catch (DataProviderException e){
            throw new ApplicationException("xxx", "error");
        }
    }
}
