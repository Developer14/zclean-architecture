package cl.mobdev.dogs.dataprovider.response;

import java.util.Arrays;

public class ClientDogDetailsResponse {

    private String status;
    private String[] message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String[] getMessage() {
        return message;
    }

    public void setMessage(String[] message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ClientDogDetailsResponse{" +
                "status='" + getStatus() + '\'' +
                ", message=" + Arrays.toString(getMessage()) +
                '}';
    }
}
