package cl.mobdev.dogs.boot.config;

import cl.mobdev.dogs.core.usecase.breeddetails.BreedDetailsUseCase;
import cl.mobdev.dogs.core.usecase.breeddetails.GetBreedDetails;
import cl.mobdev.dogs.core.usecase.breedlist.BreedListUseCase;
import cl.mobdev.dogs.core.usecase.breedlist.GetBreedList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

    @Bean
    public BreedListUseCase breedListUseCase(GetBreedList getBreedList){
        return new BreedListUseCase(getBreedList);
    }

    @Bean
    public BreedDetailsUseCase breedDetailsUseCase(GetBreedDetails getBreedDetails){
        return new BreedDetailsUseCase(getBreedDetails);
    }
}
