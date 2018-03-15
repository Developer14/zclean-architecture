package cl.mobdev.dogs.dto;

import org.junit.Test;

public class DogImageDtoTest {

    @Test
    public void test(){
        DogImageDto dto = new DogImageDto();
        dto.setUrl("http://xxxx");

        dto.toString();
    }
}
