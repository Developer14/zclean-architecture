package cl.mobdev.dogs.core.usecase.exception;

public class DataProviderException extends Exception {

    public DataProviderException(String message, Throwable throwable){
        super(message, throwable);
    }
}
