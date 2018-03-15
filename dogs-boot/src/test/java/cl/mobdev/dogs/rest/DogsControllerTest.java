package cl.mobdev.dogs.rest;

import cl.mobdev.dogs.config.TestContext;
import cl.mobdev.dogs.core.entity.Dog;
import cl.mobdev.dogs.core.entity.DogImage;
import cl.mobdev.dogs.core.usecase.breeddetails.BreedDetailsUseCase;
import cl.mobdev.dogs.core.usecase.breeddetails.GetBreedDetails;
import cl.mobdev.dogs.core.usecase.breedlist.BreedListUseCase;
import cl.mobdev.dogs.core.usecase.breedlist.GetBreedList;
import cl.mobdev.dogs.core.usecase.exception.ApplicationException;
import cl.mobdev.dogs.dto.DogDto;
import cl.mobdev.dogs.dto.DogListDto;
import cl.mobdev.dogs.dto.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class})
@WebAppConfiguration
public class DogsControllerTest {

    private MockMvc mockMvc;
    @Autowired private WebApplicationContext webApplicationContext;

    @Mock private GetBreedList getBreedList;
    @Mock private GetBreedDetails getBreedDetails;
    @Mock private BreedListUseCase breedListUseCase;
    @Mock private BreedDetailsUseCase breedDetailsUseCase;
    @Mock private ConversionService conversionService;
    @Autowired @InjectMocks
    private DogsController dogsController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void allDogsTest() throws Exception{

        List<String> dogs = new ArrayList<String>();
        DogListDto dogListDto = new DogListDto();
        dogListDto.setDogsList(dogs);

        when(breedListUseCase.getBreedList()).thenReturn(dogs);
        when(conversionService.convert(any(List.class), any(Class.class))).thenReturn(dogListDto);


        ResponseDto<DogListDto> responseDto = new ResponseDto<DogListDto>();
        responseDto.setStatusCode("0");
        responseDto.setStatusMessage("Ok");
        responseDto.setData(dogListDto);

        mockMvc.perform(get("/dogs/all")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dogs)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }

    @Test
    public void allDogsTestException() throws Exception{

        List<String> dogs = new ArrayList<String>();
        DogListDto dogListDto = new DogListDto();
        dogListDto.setDogsList(dogs);

        when(breedListUseCase.getBreedList()).thenThrow(new ApplicationException("111", "error"));
        when(conversionService.convert(any(List.class), any(Class.class))).thenReturn(dogListDto);


        ResponseDto<DogListDto> responseDto = new ResponseDto<DogListDto>();
        responseDto.setStatusCode("111");
        responseDto.setStatusMessage("error");
        responseDto.setData(null);

        mockMvc.perform(get("/dogs/all")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dogs)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }

    @Test
    public void breedDetailsTest() throws Exception{

        Dog dog = new Dog();
        dog.setSubBreeds(new ArrayList<String>());
        dog.setImages(new ArrayList<DogImage>());
        DogDto dogDto = new DogDto();
        dogDto.setBreed(dog.getBreed());
        dogDto.setSubBreeds(dog.getSubBreeds());

        ResponseDto<DogDto> responseDto = new ResponseDto<DogDto>();
        responseDto.setStatusCode("0");
        responseDto.setStatusMessage("Ok");
        responseDto.setData(dogDto);

        when(breedDetailsUseCase.breedDetailsUseCase(anyString())).thenReturn(dog);
        when(conversionService.convert(any(Dog.class), any(Class.class))).thenReturn(dogDto);

        mockMvc.perform(get("/dogs/pug/details")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dogDto)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }

    @Test
    public void breedDetailsTestException() throws Exception{

        Dog dog = new Dog();
        dog.setSubBreeds(new ArrayList<String>());
        dog.setImages(new ArrayList<DogImage>());
        DogDto dogDto = new DogDto();
        dogDto.setBreed(dog.getBreed());
        dogDto.setSubBreeds(dog.getSubBreeds());

        when(breedDetailsUseCase.breedDetailsUseCase(anyString())).thenThrow(new ApplicationException("111", "error"));
        when(conversionService.convert(any(Dog.class), any(Class.class))).thenReturn(dogDto);

        ResponseDto<DogDto> responseDto = new ResponseDto<DogDto>();
        responseDto.setStatusCode("111");
        responseDto.setStatusMessage("error");
        responseDto.setData(null);

        mockMvc.perform(get("/dogs/pug/details")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dogDto)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }
}
