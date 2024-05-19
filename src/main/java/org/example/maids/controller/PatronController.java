package org.example.maids.controller;

import jakarta.validation.Valid;
import org.example.maids.models.Patron;
import org.example.maids.services.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
@Validated
public class PatronController {

    @Autowired
    PatronService patronService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Patron> getAllPatrons(){
        return this.patronService.getAllPatrons();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Patron addPatronDetails(@RequestBody @Valid Patron Patron){
        return this.patronService.addPatronDetails(Patron);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Patron getPatronById(@PathVariable Long id){
        return this.patronService.getPatronDetails(id);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Patron updatePatronById(@PathVariable Long id, @RequestBody @Valid Patron Patron){
        return this.patronService.updatePatronDetails(id,Patron);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> removePatronById(@PathVariable Long id){
        this.patronService.removePatronById(id);
        return ResponseEntity.ok().build();
    }

}
