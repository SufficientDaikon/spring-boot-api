package com.ahmed.Library.repositories;

import com.ahmed.Library.entities.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronRepository extends JpaRepository<Patron, Long> {
}
