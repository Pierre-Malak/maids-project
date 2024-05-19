package org.example.maids.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.maids.models.Patron;
import org.example.maids.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PatronService {
   
    @Autowired
    PatronRepository patronRepository;

    public List<Patron> getAllPatrons(){
        return this.patronRepository.findAll();
    }
    @Cacheable(value = "maidsCache", key = "#root.targetClass.toString() + #id.toString()")
    public Patron getPatronDetails(Long id){
        return this.patronRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patron not found with id: " + id));
    }
    @Transactional
    @CachePut(value = "maidsCache", key = "#root.targetClass.toString() + #id.toString()")
    public Patron updatePatronDetails(Long id, Patron newPatron){
        Patron oldPatron = this.getPatronDetails(id);
        oldPatron.setName(newPatron.getName());
        oldPatron.setPhoneNumber(newPatron.getPhoneNumber());
        return this.patronRepository.save(oldPatron);
    }
    @Transactional
    public Patron addPatronDetails(Patron Patron){
        return this.patronRepository.save(Patron);
    }

    @Transactional
    @CacheEvict(value = "maidsCache", key = "#root.targetClass.toString() + #id.toString()")
    public void removePatronById(Long id){
        this.patronRepository.deleteById(id);
    }
    
    
}
