package cl.mobdev.dogs.core.exception;

import cl.mobdev.dogs.core.usecase.exception.ApplicationException;
import org.junit.Assert;
import org.junit.Test;

public class ApplicationExceptionTest {

    @Test
    public void test(){
        ApplicationException exception = new ApplicationException("", "");
        exception.setCode("code");
        exception.setMessage("message");

        Assert.assertEquals("code", exception.getCode());
        Assert.assertEquals("message", exception.getMessage());
    }
}
