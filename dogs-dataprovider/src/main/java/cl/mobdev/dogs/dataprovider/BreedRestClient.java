package cl.mobdev.dogs.dataprovider;

import cl.mobdev.dogs.core.entity.Dog;
import cl.mobdev.dogs.core.entity.DogImage;
import cl.mobdev.dogs.core.usecase.breeddetails.GetBreedDetails;
import cl.mobdev.dogs.core.usecase.breedlist.GetBreedList;
import cl.mobdev.dogs.core.usecase.exception.DataProviderException;
import cl.mobdev.dogs.dataprovider.response.ClientDogDetailsResponse;
import cl.mobdev.dogs.dataprovider.response.ClientDogListResponse;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class BreedRestClient implements GetBreedList, GetBreedDetails {

    private final String baseUrl = "https://dog.ceo/api";
    private final String allDogsUrl = baseUrl + "/breeds/list/all";
    private final String breedImagesUrl = baseUrl + "/breed/{breedName}/images";
    private final String subBreedsUrl = baseUrl + "/breed/{breedName}/list";

    private RestTemplate restTemplate;

    public BreedRestClient(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public List<String> getBreedList() throws DataProviderException {

        try {

            ClientDogListResponse response = restTemplate.getForObject(allDogsUrl, ClientDogListResponse.class);

            List<String> list = new ArrayList<String>();
            if(response.getMessage() != null)
                for (Map.Entry<String, String[]> entry: response.getMessage().entrySet())
                    list.add(entry.getKey());

            return list;

        }catch (Exception e){
            throw new DataProviderException(e.getMessage(), e);
        }

    }

    public Dog getBreedDetails(String breed) throws DataProviderException {

        Map<String, String> params = new HashMap<String, String>();

        try {
            params.put("breedName", breed);

            ClientDogDetailsResponse subBreedsResponse = restTemplate.getForObject(subBreedsUrl, ClientDogDetailsResponse.class, params);
            ClientDogDetailsResponse imagesResponse = restTemplate.getForObject(breedImagesUrl, ClientDogDetailsResponse.class, params);

            Dog dog = new Dog();
            dog.setBreed(breed);
            dog.setSubBreeds(Arrays.asList(subBreedsResponse.getMessage()));
            dog.setImages(convertToDogImages(imagesResponse.getMessage()));
            return dog;

        }catch (Exception e){
            throw new DataProviderException(e.getMessage(), e);
        }

    }

    private List<DogImage> convertToDogImages(String[] images){
        List<DogImage> dogImages = new ArrayList<DogImage>();
        for (String img: images) {
            DogImage dogImage = new DogImage();
            dogImage.setUrl(img);
            dogImages.add(dogImage);
        }

        return dogImages;
    }
}
