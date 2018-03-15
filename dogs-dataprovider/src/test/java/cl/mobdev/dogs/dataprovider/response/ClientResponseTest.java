package cl.mobdev.dogs.dataprovider.response;

import org.junit.Test;

public class ClientResponseTest {

    @Test
    public void test(){
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setMessage(new String[]{});
        clientResponse.setStatus("Ok");

        clientResponse.toString();
    }
}
