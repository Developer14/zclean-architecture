package cl.mobdev.dogs.dataprovider.response;

import org.junit.Test;

import java.util.HashMap;

public class ClientDogListResponseTest {

    @Test
    public void test(){
        ClientDogListResponse clientDogListResponse = new ClientDogListResponse();
        clientDogListResponse.setMessage(new HashMap<String, String[]>());
        clientDogListResponse.setStatus("Ok");

        clientDogListResponse.toString();
    }
}
