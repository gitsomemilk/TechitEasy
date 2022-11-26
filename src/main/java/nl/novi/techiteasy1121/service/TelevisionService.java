package nl.novi.techiteasy1121.service;

import nl.novi.techiteasy1121.Dtos.TelevisionDto;
import nl.novi.techiteasy1121.Dtos.TelevisionInputDto;
import nl.novi.techiteasy1121.exceptions.RecordNotFoundException;
import nl.novi.techiteasy1121.models.Television;
import nl.novi.techiteasy1121.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// @Servie annotatie boven de klasse zodat springboot het herkent
@Service
public class TelevisionService {

    // import van de repository in de service inplaats van in de controller
    // constructor injection BEST PRACTICE!!!!!!
    private final TelevisionRepository repos;

    public TelevisionService(TelevisionRepository repos) {this.repos = repos;}


    // Vanuit de repository kunnen we een lijst van Televisions krijgen, maar de communicatie container tussen Service en
    // Controller is de Dto. We moeten de Televisions dus vertalen naar TelevisionDtos. Dit moet een voor een, omdat
    // de translateToDto() methode geen lijst accepteert als argument, dus gebruiken we een for-loop.
    public List<TelevisionDto> getAllTelevisions(){

        List<Television> tvList = repos.findAll();
        List<TelevisionDto> tvListDto = new ArrayList<>();

        for (Television tv : tvList) {
            TelevisionDto dto = transferToDto(tv);
            tvListDto.add(dto);
        }
        return tvListDto;


    }


    public List<TelevisionDto> getAllTelevisionsByBrand(String brand){

        List<Television> tvList = repos.findAllTelevisionsByBrandEqualsIgnoreCase(brand);
        List<TelevisionDto> tvListDto = new ArrayList<>();

        for(Television tv : tvList){
            TelevisionDto dto = transferToDto(tv);
            tvListDto.add(dto);
        }
        return  tvListDto;
    }


    public TelevisionDto getTelevisionById(Long id) {
        Optional<Television> televisionOptional = repos.findById(id);
        if(televisionOptional.isPresent()){
            Television tv = televisionOptional.get();
            return transferToDto(tv);
        } else {
            throw new RecordNotFoundException("geen televisie gevonden");
        }

    }




    // In deze methode moeten we twee keer een vertaal methode toepassen.
    // De eerste keer van dto naar televsion, omdat de parameter een dto is.
    // De tweede keer van television naar dto, omdat de return waarde een dto is.

    public TelevisionDto addTelevision(TelevisionInputDto dto) {
        Television tv = transferToTelevision(dto);
        repos.save(tv);

        return  transferToDto(tv);

    }

    public void deleteTelevision(@RequestBody Long id) {

        repos.deleteById(id);
    }

    public TelevisionDto updateTelevision(Long id, TelevisionInputDto newTelevision) {

        Optional<Television> televisionOptional = repos.findById(id);
        if(televisionOptional.isPresent()) {

            Television television1 = televisionOptional.get();

            television1.setAmbiLight(newTelevision.getAmbiLight());
            television1.setAvailableSize(newTelevision.getAvailableSize());
            television1.setAmbiLight(newTelevision.getAmbiLight());
            television1.setBluetooth(newTelevision.getBluetooth());
            television1.setBrand(newTelevision.getBrand());
            television1.setHdr(newTelevision.getHdr());
            television1.setName(newTelevision.getName());
            television1.setOriginalStock(newTelevision.getOriginalStock());
            television1.setPrice(newTelevision.getPrice());
            television1.setRefreshRate(newTelevision.getRefreshRate());
            television1.setScreenQuality(newTelevision.getScreenQuality());
            television1.setScreenType(newTelevision.getScreenType());
            television1.setSmartTv(newTelevision.getSmartTv());
            television1.setSold(newTelevision.getSold());
            television1.setType(newTelevision.getType());
            television1.setVoiceControl(newTelevision.getVoiceControl());
            television1.setWifi(newTelevision.getWifi());
            Television returnTelevision = repos.save(television1);

            return transferToDto(returnTelevision);

        } else {

            throw new RecordNotFoundException("geen televisie gevonden");

        }




    }

    // Dit is de vertaal methode van TelevisionInputDto naar Television.
    public Television transferToTelevision(TelevisionInputDto dto){
        var television = new Television();

        television.setType(dto.getType());
        television.setBrand(dto.getBrand());
        television.setName(dto.getName());
        television.setPrice(dto.getPrice());
        television.setAvailableSize(dto.getAvailableSize());
        television.setRefreshRate(dto.getRefreshRate());
        television.setScreenType(dto.getScreenType());
        television.setScreenQuality(dto.getScreenQuality());
        television.setSmartTv(dto.getSmartTv());
        television.setWifi(dto.getWifi());
        television.setVoiceControl(dto.getVoiceControl());
        television.setHdr(dto.getHdr());
        television.setBluetooth(dto.getBluetooth());
        television.setAmbiLight(dto.getAmbiLight());
        television.setOriginalStock(dto.getOriginalStock());
        television.setSold(dto.getSold());

        return television;
    }





    // Dit is de vertaal methode van Television naar TelevisionDto
    public TelevisionDto transferToDto(Television tv) {
        TelevisionDto dto = new TelevisionDto();

        dto.setId(tv.getId());
        dto.setType(tv.getType());
        dto.setBrand(tv.getBrand());
        dto.setName(tv.getName());
        dto.setPrice(tv.getPrice());
        dto.setAvailableSize(tv.getAvailableSize());
        dto.setRefreshRate(tv.getRefreshRate());
        dto.setScreenType(tv.getScreenType());
        dto.setScreenQuality(tv.getScreenQuality());
        dto.setSmartTv(tv.getSmartTv());
        dto.setWifi(tv.getWifi());
        dto.setVoiceControl(tv.getVoiceControl());
        dto.setHdr(tv.getHdr());
        dto.setBluetooth(tv.getBluetooth());
        dto.setAmbiLight(tv.getAmbiLight());
        dto.setOriginalStock(tv.getOriginalStock());
        dto.setSold(tv.getSold());

        return dto;
    }





}






