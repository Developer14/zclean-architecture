package cl.mobdev.dogs.converter;

import cl.mobdev.dogs.dto.DogListDto;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class DogListConverter implements Converter<List<String>, DogListDto> {

    @Override
    public DogListDto convert(List<String> dogs) {
        DogListDto dogListDto = new DogListDto();
        for (String dog: dogs) {
            if(dogListDto.getDogsList() == null)
                dogListDto.setDogsList(new ArrayList<String>());

            dogListDto.getDogsList().add(dog);
        }
        return dogListDto;
    }
}
