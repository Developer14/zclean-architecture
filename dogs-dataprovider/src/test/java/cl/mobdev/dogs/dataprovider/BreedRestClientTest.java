package cl.mobdev.dogs.dataprovider;


import cl.mobdev.dogs.core.entity.Dog;
import cl.mobdev.dogs.core.usecase.exception.DataProviderException;
import cl.mobdev.dogs.dataprovider.response.ClientDogDetailsResponse;
import cl.mobdev.dogs.dataprovider.response.ClientDogDetailsResponseTest;
import cl.mobdev.dogs.dataprovider.response.ClientDogListResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BreedRestClientTest {

    @Mock private RestTemplate restTemplate;
    @InjectMocks
    private BreedRestClient breedRestClient = new BreedRestClient(restTemplate);

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void allDogsTest() throws Exception{

        ClientDogListResponse clientDogListResponse = new ClientDogListResponse();
        Map<String, String[]> map = new HashMap<String, String[]>();
        map.put("Haunt", null);
        map.put("Pug", null);
        clientDogListResponse.setMessage(map);
        clientDogListResponse.setStatus("Ok");

        Mockito.when(restTemplate
                .getForObject(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(clientDogListResponse);
        List<String> list = breedRestClient.getBreedList();

        Assert.assertNotNull(list);
    }

    @Test(expected = DataProviderException.class)
    public void allDogsTestException() throws Exception{

        Mockito.when(restTemplate
                .getForObject(Mockito.anyString(), Mockito.any(Class.class))).thenThrow(new RuntimeException());
        breedRestClient.getBreedList();

    }

    @Test
    @SuppressWarnings("unchecked")
    public void breedDetailsTest() throws Exception{

        ClientDogDetailsResponse subBreedsResponse = new ClientDogDetailsResponse();
        subBreedsResponse.setStatus("Ok");
        subBreedsResponse.setMessage(new String[]{"nnnnn", "mmmmm"});
        ClientDogDetailsResponse imagesResponse = new ClientDogDetailsResponse();
        imagesResponse.setStatus("Ok");
        imagesResponse.setMessage(new String[]{"http://...", "http://..."});

        Mockito.when(restTemplate
                .getForObject(Mockito.anyString(), Mockito.any(Class.class), Mockito.any(Map.class)))
                .thenReturn(subBreedsResponse);
        Mockito.when(restTemplate
                .getForObject(Mockito.anyString(), Mockito.any(Class.class), Mockito.any(Map.class)))
                .thenReturn(imagesResponse);

        Dog dog = breedRestClient.getBreedDetails("Pug");

        Assert.assertEquals("Pug", dog.getBreed());
    }

    @Test(expected = DataProviderException.class)
    @SuppressWarnings("unchecked")
    public void breedDetailsTestException() throws Exception{

        Mockito.when(restTemplate
                .getForObject(Mockito.anyString(), Mockito.any(Class.class), Mockito.any(Map.class)))
                .thenThrow(new RuntimeException());

        breedRestClient.getBreedDetails("Pug");
    }
}
