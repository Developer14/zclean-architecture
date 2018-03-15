package cl.mobdev.dogs.dto;

public class DogImageDto {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "DogImageDto{" +
                "url='" + getUrl() + '\'' +
                '}';
    }
}
