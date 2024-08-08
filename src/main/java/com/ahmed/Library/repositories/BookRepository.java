package com.ahmed.Library.repositories;

import com.ahmed.Library.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
