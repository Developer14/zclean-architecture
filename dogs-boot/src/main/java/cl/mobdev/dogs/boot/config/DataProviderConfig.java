package cl.mobdev.dogs.boot.config;

import cl.mobdev.dogs.dataprovider.BreedRestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DataProviderConfig {

    @Bean
    public BreedRestClient breedRestClient(){
        return new BreedRestClient(new RestTemplate());
    }
}
