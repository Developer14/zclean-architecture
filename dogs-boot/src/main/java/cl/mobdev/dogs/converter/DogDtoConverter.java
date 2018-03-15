package cl.mobdev.dogs.converter;

import cl.mobdev.dogs.core.entity.Dog;
import cl.mobdev.dogs.core.entity.DogImage;
import cl.mobdev.dogs.dto.DogDto;
import cl.mobdev.dogs.dto.DogImageDto;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class DogDtoConverter implements Converter<Dog, DogDto> {

    public DogDto convert(Dog dog) {
        DogDto dto = new DogDto();
        dto.setBreed(dog.getBreed());
        dto.setSubBreeds(dog.getSubBreeds());
        dto.setImages(getImagesDto(dog.getImages()));

        return dto;
    }

    private List<DogImageDto> getImagesDto(List<DogImage> images){
        List<DogImageDto> imageDtos = new ArrayList<DogImageDto>();
        for (DogImage image: images) {
            DogImageDto dto = new DogImageDto();
            dto.setUrl(image.getUrl());
            imageDtos.add(dto);
        }
        return imageDtos;
    }
}
