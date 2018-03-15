package cl.mobdev.dogs.dataprovider;


import cl.mobdev.dogs.core.entity.Dog;
import cl.mobdev.dogs.core.usecase.exception.DataProviderException;
import cl.mobdev.dogs.dataprovider.response.ClientResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

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

        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setMessage(new String[]{"Haunt", "Pug"});
        clientResponse.setStatus("Ok");

        Mockito.when(restTemplate
                .getForObject(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(clientResponse);
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

        ClientResponse subBreedsResponse = new ClientResponse();
        subBreedsResponse.setStatus("Ok");
        subBreedsResponse.setMessage(new String[]{"nnnnn", "mmmmm"});
        ClientResponse imagesResponse = new ClientResponse();
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
