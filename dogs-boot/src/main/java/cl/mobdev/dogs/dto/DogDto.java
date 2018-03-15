package cl.mobdev.dogs.dto;

import java.util.List;

public class DogDto {

    private String breed;
    private List<String> subBreeds;
    private List<DogImageDto> images;

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

    public List<DogImageDto> getImages() {
        return images;
    }

    public void setImages(List<DogImageDto> images) {
        this.images = images;
    }
}
