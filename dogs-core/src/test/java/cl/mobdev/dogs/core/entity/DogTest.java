package cl.mobdev.dogs.core.entity;

import org.junit.Test;

import java.util.ArrayList;

public class DogTest {

    @Test
    public void test(){

        Dog dog = new Dog();
        dog.setBreed("Pug");
        dog.setSubBreeds(new ArrayList<String>());
        dog.setImages(new ArrayList<DogImage>());

        dog.toString();
    }
}
