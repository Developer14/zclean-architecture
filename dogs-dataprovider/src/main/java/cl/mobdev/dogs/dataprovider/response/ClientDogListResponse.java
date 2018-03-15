package cl.mobdev.dogs.dataprovider.response;

import java.util.Arrays;
import java.util.Map;

public class ClientDogListResponse {

    private String status;
    private Map<String, String[]> message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, String[]> getMessage() {
        return message;
    }

    public void setMessage(Map<String, String[]> message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ClientDogListResponse{" +
                "status='" + getStatus() + '\'' +
                ", message=" + getMessage() +
                '}';
    }
}
