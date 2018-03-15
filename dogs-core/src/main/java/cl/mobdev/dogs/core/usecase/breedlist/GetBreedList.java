package cl.mobdev.dogs.core.usecase.breedlist;

import cl.mobdev.dogs.core.usecase.exception.DataProviderException;

import java.util.List;

public interface GetBreedList {

    List<String> getBreedList() throws DataProviderException;
}
