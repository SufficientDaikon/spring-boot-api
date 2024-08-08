package com.ahmed.Library.services;

import com.ahmed.Library.entities.Patron;
import com.ahmed.Library.repositories.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PatronService {
    @Autowired
    private PatronRepository patronRepository;

    public List<Patron> findAll() {
        return patronRepository.findAll();
    }

    public Patron findById(Long id) {
        return patronRepository.findById(id).orElse(null);
    }

    @Transactional
    public Patron save(Patron patron) {
        return patronRepository.save(patron);
    }

    @Transactional
    public void deleteById(Long id) {
        patronRepository.deleteById(id);
    }
}
