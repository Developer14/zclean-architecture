package cl.mobdev.dogs.core.entity;

public class DogImage {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "DogImage{" +
                "url='" + getUrl() + '\'' +
                '}';
    }
}
