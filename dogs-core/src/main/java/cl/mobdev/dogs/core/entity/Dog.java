package cl.mobdev.dogs.core.entity;

import java.util.List;

public class Dog {

    private String breed;
    private List<String> subBreeds;
    private List<DogImage> images;

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public List<String> getSubBreeds() {
        return subBreeds;
    }

    public void setSubBreeds(List<String> subBreeds) {
        this.subBreeds = subBreeds;
    }

    public List<DogImage> getImages() {
        return images;
    }

    public void setImages(List<DogImage> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "breed='" + getBreed() + '\'' +
                ", subBreeds=" + getSubBreeds() +
                ", images=" + getImages() +
                '}';
    }
}
