package com.ahmed.Library.controllers;

import com.ahmed.Library.entities.Patron;
import com.ahmed.Library.services.PatronService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatronControllerTest {

    @Mock
    private PatronService patronService;

    @InjectMocks
    private PatronController patronController;

    private Patron patron;

    @BeforeEach
    public void setUp() {
        patron = new Patron();
        patron.setId(1L);
        patron.setName("John Doe");
        patron.setContactInformation("john.doe@example.com");
    }

    @Test
    public void testGetAllPatrons() {
        when(patronService.findAll()).thenReturn(Arrays.asList(patron));
        List<Patron> result = patronController.getAllPatrons();
        assertEquals(1, result.size());
        assertEquals(patron, result.get(0));
    }

    @Test
    public void testGetPatronById() {
        when(patronService.findById(1L)).thenReturn(patron);
        ResponseEntity<Patron> response = patronController.getPatronById(1L);
        assertEquals(ResponseEntity.ok(patron), response);
    }

    @Test
    public void testGetPatronByIdNotFound() {
        when(patronService.findById(1L)).thenReturn(null);
        ResponseEntity<Patron> response = patronController.getPatronById(1L);
        assertEquals(ResponseEntity.notFound().build(), response);
    }

    @Test
    public void testCreatePatron() {
        when(patronService.save(any(Patron.class))).thenReturn(patron);
        ResponseEntity<Patron> response = patronController.createPatron(patron);
        assertEquals(ResponseEntity.ok(patron), response);
    }

    @Test
    public void testUpdatePatron() {
        when(patronService.findById(1L)).thenReturn(patron);
        when(patronService.save(any(Patron.class))).thenReturn(patron);
        ResponseEntity<Patron> response = patronController.updatePatron(1L, patron);
        assertEquals(ResponseEntity.ok(patron), response);
    }

    @Test
    public void testUpdatePatronNotFound() {
        when(patronService.findById(1L)).thenReturn(null);
        ResponseEntity<Patron> response = patronController.updatePatron(1L, patron);
        assertEquals(ResponseEntity.notFound().build(), response);
    }

    @Test
    public void testDeletePatron() {
        when(patronService.findById(1L)).thenReturn(patron);
        doNothing().when(patronService).deleteById(1L);
        ResponseEntity<Void> response = patronController.deletePatron(1L);
        assertEquals(ResponseEntity.noContent().build(), response);
    }

    @Test
    public void testDeletePatronNotFound() {
        when(patronService.findById(1L)).thenReturn(null);
        ResponseEntity<Void> response = patronController.deletePatron(1L);
        assertEquals(ResponseEntity.notFound().build(), response);
    }
}
