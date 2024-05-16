package org.example.maids.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.maids.models.Patron;
import org.example.maids.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatronService {
   
    @Autowired
    PatronRepository patronRepository;

    public List<Patron> getAllPatrons(){
        return this.patronRepository.findAll();
    }

    public Patron getPatronDetails(Long id){
        return this.patronRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patron not found with id: " + id));
    }

    public Patron updatePatronDetails(Long id, Patron newPatron){
        Patron oldPatron = this.getPatronDetails(id);
        oldPatron.setName(newPatron.getName());
        oldPatron.setContactInformation(newPatron.getContactInformation());
        return this.patronRepository.save(oldPatron);
    }

    public Patron addPatronDetails(Patron Patron){
        return this.patronRepository.save(Patron);
    }

    public void removePatronById(Long id){
        this.patronRepository.deleteById(id);
    }
    
    
}
