package org.example.maids.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Priority;
import org.example.maids.models.Patron;
import org.example.maids.services.PatronService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class PatronsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PatronService patronService;


    @Test
    void testGetAllPatrons() throws Exception {
        when(patronService.getAllPatrons()).thenReturn(Collections.singletonList(new Patron()));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/patrons")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testAddPatronByID() throws Exception {
        Patron patronToAdd = new Patron();
        patronToAdd.setId(1L);
        patronToAdd.setPhoneNumber("01227371089");
        patronToAdd.setName("Pierre");

        when(patronService.addPatronDetails(any(Patron.class))).thenReturn(patronToAdd);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/patrons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(patronToAdd)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetPatronsById() throws Exception {
        Long patronId = 1L;
        Patron patron = new Patron();
        patron.setId(patronId);
        patron.setName("Pierre");
        patron.setPhoneNumber("01227371089");
        when(patronService.getPatronDetails(any())).thenReturn(patron);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/patrons/{id}", patronId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(patron.getName()));
    }

    @Test
    void testUpdatePatron() throws Exception {
        Long patronId = 1L;
        Patron patron = new Patron();
        patron.setId(patronId);
        patron.setName("Pierre");
        patron.setPhoneNumber("01227371089");

        Patron updatedPatron = new Patron();
        updatedPatron.setId(patronId);
        updatedPatron.setName("Pierre");
        updatedPatron.setPhoneNumber("01227371089");
        // Mock patronService.updatepatronDetails() to return the updated patron

        when(patronService.getPatronDetails(eq(patronId))).thenReturn(patron);
        when(patronService.updatePatronDetails(eq(patronId), any(Patron.class))).thenReturn(updatedPatron);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/patrons/{id}", patronId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedPatron)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(updatedPatron.getName()));
    }


    @Test
    void testRemovePatronById() throws Exception {
        Long patronId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/patrons/{id}", patronId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
