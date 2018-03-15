package cl.mobdev.dogs.config;

import cl.mobdev.dogs.converter.DogDtoConverter;
import cl.mobdev.dogs.converter.DogListConverter;
import cl.mobdev.dogs.core.usecase.breeddetails.BreedDetailsUseCase;
import cl.mobdev.dogs.core.usecase.breeddetails.GetBreedDetails;
import cl.mobdev.dogs.core.usecase.breedlist.BreedListUseCase;
import cl.mobdev.dogs.core.usecase.breedlist.GetBreedList;
import cl.mobdev.dogs.rest.DogsController;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableWebMvc
public class TestContext {

    @Bean
    public DogsController dogsController(){
        return new DogsController(breedListUseCase(), breedDetailsUseCase());
    }

    @Bean
    public BreedListUseCase breedListUseCase(){
        return Mockito.mock(BreedListUseCase.class);
    }

    @Bean
    public BreedDetailsUseCase breedDetailsUseCase(){
        return Mockito.mock(BreedDetailsUseCase.class);
    }

    @Bean
    public GetBreedDetails getBreedDetails(){
        return Mockito.mock(GetBreedDetails.class);
    }

    @Bean
    public GetBreedList getBreedList(){
        return Mockito.mock(GetBreedList.class);
    }

    @Bean
    public ConversionService conversionService(){
        ConversionServiceFactoryBean factoryBean = new ConversionServiceFactoryBean();
        factoryBean.setConverters(converters());
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    private Set<Converter> converters(){
        Set<Converter> converters = new HashSet<Converter>();
        converters.add(new DogDtoConverter());
        converters.add(new DogListConverter());
        return converters;
    }
}
