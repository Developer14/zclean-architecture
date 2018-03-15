package cl.mobdev.dogs.dataprovider.response;

import org.junit.Test;

public class ClientDogDetailsResponseTest {

    @Test
    public void test(){
        ClientDogDetailsResponse response = new ClientDogDetailsResponse();
        response.setStatus("Ok");
        response.setMessage(new String[]{"",""});

        response.toString();
    }
}
