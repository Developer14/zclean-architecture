package cl.mobdev.dogs.converter;

import cl.mobdev.dogs.core.entity.Dog;
import cl.mobdev.dogs.core.entity.DogImage;
import cl.mobdev.dogs.dto.DogDto;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DogDtoConverterTest {

    @Test
    public void converterTest(){
        DogDtoConverter dogDtoConverter = new DogDtoConverter();

        Dog dog = new Dog();
        dog.setBreed("Pug");
        dog.setSubBreeds(new ArrayList<String>());

        List<DogImage> images = new ArrayList<DogImage>();
        images.add(new DogImage());

        dog.setImages(images);
        DogDto dto = dogDtoConverter.convert(dog);

        Assert.assertEquals("Pug", dto.getBreed());
    }
}
