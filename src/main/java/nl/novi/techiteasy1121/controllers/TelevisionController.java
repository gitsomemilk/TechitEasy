package nl.novi.techiteasy1121.controllers;


import nl.novi.techiteasy1121.Dtos.TelevisionDto;
import nl.novi.techiteasy1121.Dtos.TelevisionInputDto;
import nl.novi.techiteasy1121.service.TelevisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TelevisionController {

    //we importeren hier de TelevisionService inplaats van de televisionRepository
    private final TelevisionService televisionService;

    // Constructor injection
    public TelevisionController(TelevisionService televisionService){
        this.televisionService = televisionService ;
    }


    @GetMapping("/televisions")
    public ResponseEntity<List<TelevisionDto>> getAllTelevisions(@RequestParam(value = "brand", required = false) Optional<String> brand) {

        List<TelevisionDto> dtos;

        if (brand.isEmpty()){


            dtos = televisionService.getAllTelevisions();


        } else {

            dtos = televisionService.getAllTelevisionsByBrand(brand.get());
        }

        // Return de televisions lijst en een 200 status
        return ResponseEntity.ok().body(dtos);

    }




    // Return 1 televisie met een specifiek id
    @GetMapping("/televisions/{id}")
    public ResponseEntity<TelevisionDto> getTelevision(@PathVariable("id") Long id) {

        TelevisionDto television = televisionService.getTelevisionById(id);


            return ResponseEntity.ok().body(television);
        }




    // We geven hier een television mee in de parameter. Zorg dat JSON object exact overeenkomt met het Television object.
    @PostMapping("/televisions")
    public ResponseEntity<TelevisionDto> addTelevision(@RequestBody TelevisionInputDto televisionInputDto) {


        TelevisionDto dto = televisionService.addTelevision(televisionInputDto);


        return ResponseEntity.created(null).body(dto);

    }

    @DeleteMapping("/televisions/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable Long id) {


        televisionService.deleteTelevision(id);


        return ResponseEntity.noContent().build();

    }

    @PutMapping("/televisions/{id}")
    public ResponseEntity<TelevisionDto> updateTelevision(@PathVariable Long id, @RequestBody TelevisionInputDto newTelevision) {

        // Haal de aan te passen tv uit de database met het gegeven id
        TelevisionDto dto = televisionService.updateTelevision(id,newTelevision);

        // Als eerste checken we of de aan te passen tv wel in de database bestaat.

            return ResponseEntity.ok().body(dto);
        }

    }


