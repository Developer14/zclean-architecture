package cl.mobdev.dogs.converter;

import cl.mobdev.dogs.dto.DogListDto;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DogListConverterTest {

    @Test
    public void test(){
        DogListConverter converter = new DogListConverter();
        List<String> dogList = new ArrayList<String>();
        dogList.add("Pug");
        DogListDto dogListDto = converter.convert(dogList);

        Assert.assertNotNull(dogListDto.getDogsList());
    }
}
