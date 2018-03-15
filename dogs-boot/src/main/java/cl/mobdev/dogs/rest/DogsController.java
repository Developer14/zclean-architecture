package cl.mobdev.dogs.rest;

import cl.mobdev.dogs.core.usecase.breeddetails.BreedDetailsUseCase;
import cl.mobdev.dogs.core.usecase.breedlist.BreedListUseCase;
import cl.mobdev.dogs.core.usecase.exception.ApplicationException;
import cl.mobdev.dogs.dto.DogDto;
import cl.mobdev.dogs.dto.DogListDto;
import cl.mobdev.dogs.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/dogs")
public class DogsController {

    private BreedListUseCase breedListUseCase;
    private BreedDetailsUseCase breedDetailsUseCase;

    @Autowired private ConversionService conversionService;

    public DogsController(BreedListUseCase breedListUseCase, BreedDetailsUseCase breedDetailsUseCase){
        this.breedListUseCase = breedListUseCase;
        this.breedDetailsUseCase = breedDetailsUseCase;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<ResponseDto<DogListDto>> allDogs(){

        ResponseDto<DogListDto> responseDto = new ResponseDto();
        try {

            responseDto.setStatusCode("0");
            responseDto.setStatusMessage("Ok");
            responseDto.setData(conversionService.convert(breedListUseCase.getBreedList(), DogListDto.class));

        }catch (ApplicationException e){
            responseDto.setStatusCode(e.getCode());
            responseDto.setStatusMessage(e.getMessage());
        }

        return new ResponseEntity<ResponseDto<DogListDto>>(responseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{breed}/details", method = RequestMethod.GET)
    public ResponseEntity<ResponseDto<DogDto>> dogDetails(@PathVariable String breed){

        ResponseDto<DogDto> responseDto = new ResponseDto<DogDto>();
        try {

            responseDto.setStatusCode("0");
            responseDto.setStatusMessage("Ok");
            responseDto.setData(conversionService
                    .convert(breedDetailsUseCase.breedDetailsUseCase(breed), DogDto.class));

        }catch (ApplicationException e){
            responseDto.setStatusCode(e.getCode());
            responseDto.setStatusMessage(e.getMessage());
        }

        return new ResponseEntity<ResponseDto<DogDto>>(responseDto, HttpStatus.OK);
    }
}
