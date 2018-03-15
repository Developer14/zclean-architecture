package cl.mobdev.dogs.boot.config;

import cl.mobdev.dogs.converter.DogDtoConverter;
import cl.mobdev.dogs.converter.DogListConverter;
import cl.mobdev.dogs.core.usecase.breeddetails.BreedDetailsUseCase;
import cl.mobdev.dogs.core.usecase.breedlist.BreedListUseCase;
import cl.mobdev.dogs.rest.DogsController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class EntryPointConfig {

    @Bean
    public DogsController dogsController(BreedListUseCase breedListUseCase, BreedDetailsUseCase breedDetailsUseCase){
        return new DogsController(breedListUseCase, breedDetailsUseCase);
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
