package org.example.maids.services;

import org.example.maids.models.Patron;
import org.example.maids.repository.PatronRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PatronServiceTest {

    @InjectMocks
    PatronService patronService;

    @Mock
    PatronRepository patronRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllPatronsTest() {
        when(patronRepository.findAll()).thenReturn(Arrays.asList(new Patron(1L,"Test","01227371089")));
        List<Patron> patrons = patronService.getAllPatrons();
        assertEquals(1, patrons.size());
    }

    @Test
    public void getPatronDetailsTest() {
        when(patronRepository.findById(1L)).thenReturn(Optional.of(new Patron(1L,"Test","01227371089")));
        Patron patron = patronService.getPatronDetails(1L);
        assertEquals("Test", patron.getName());
    }

    @Test
    public void updatePatronDetailsTest() {
        Patron oldPatron = new Patron(1L,"Test","01227371089");
        Patron newPatron = new Patron(1L,"Updated","01227371089");
        when(patronRepository.findById(1L)).thenReturn(Optional.of(oldPatron));
        when(patronRepository.save(oldPatron)).thenReturn(newPatron);
        Patron updatedPatron = patronService.updatePatronDetails(1L, newPatron);
        assertEquals("Updated", updatedPatron.getName());
    }

    @Test
    public void addPatronDetailsTest() {
        Patron patron = new Patron(1L,"Test","01227371089");
        when(patronRepository.save(patron)).thenReturn(patron);
        Patron savedPatron = patronService.addPatronDetails(patron);
        assertEquals("Test", savedPatron.getName());
    }

    @Test
    public void removePatronByIdTest() {
        doNothing().when(patronRepository).deleteById(1L);
        patronService.removePatronById(1L);
        verify(patronRepository, times(1)).deleteById(1L);
    }
}
