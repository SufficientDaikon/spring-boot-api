package com.ahmed.Library.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Test
    public void testGetAllBooks() {
        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Book 1");

        Book book2 = new Book();
        book2.setId(2L);
        book2.setTitle("Book 2");

        List<Book> books = Arrays.asList(book1, book2);
        when(bookService.findAll()).thenReturn(books);

        List<Book> result = bookController.getAllBooks();
        assertEquals(2, result.size());
        verify(bookService, times(1)).findAll();
    }

    @Test
    public void testGetBookById() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Book 1");

        when(bookService.findById(1L)).thenReturn(book);

        ResponseEntity<Book> result = bookController.getBookById(1L);
        assertEquals(200, result.getStatusCodeValue());
        assertNotNull(result.getBody());
        assertEquals(1L, result.getBody().getId());
        verify(bookService, times(1)).findById(1L);
    }

    @Test
    public void testGetBookByIdNotFound() {
        when(bookService.findById(1L)).thenReturn(null);

        ResponseEntity<Book> result = bookController.getBookById(1L);
        assertEquals(404, result.getStatusCodeValue());
        verify(bookService, times(1)).findById(1L);
    }

    @Test
    public void testAddBook() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Book 1");

        when(bookService.save(any(Book.class))).thenReturn(book);

        ResponseEntity<Book> result = bookController.addBook(book);
        assertEquals(200, result.getStatusCodeValue());
        assertNotNull(result.getBody());
        assertEquals(1L, result.getBody().getId());
        verify(bookService, times(1)).save(book);
    }

    @Test
    public void testUpdateBook() {
        Book existingBook = new Book();
        existingBook.setId(1L);
        existingBook.setTitle("Existing Book");

        Book updatedBook = new Book();
        updatedBook.setId(1L);
        updatedBook.setTitle("Updated Book");

        when(bookService.findById(1L)).thenReturn(existingBook);
        when(bookService.save(any(Book.class))).thenReturn(updatedBook);

        ResponseEntity<Book> result = bookController.updateBook(1L, updatedBook);
        assertEquals(200, result.getStatusCodeValue());
        assertNotNull(result.getBody());
        assertEquals("Updated Book", result.getBody().getTitle());
        verify(bookService, times(1)).findById(1L);
        verify(bookService, times(1)).save(existingBook);
    }

    @Test
    public void testDeleteBook() {
        Book book = new Book();
        book.setId(1L);

        when(bookService.findById(1L)).thenReturn(book);
        doNothing().when(bookService).deleteById(1L);

        ResponseEntity<Void> result = bookController.deleteBook(1L);
        assertEquals(204, result.getStatusCodeValue());
        verify(bookService, times(1)).findById(1L);
        verify(bookService, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteBookNotFound() {
        when(bookService.findById(1L)).thenReturn(null);

        ResponseEntity<Void> result = bookController.deleteBook(1L);
        assertEquals(404, result.getStatusCodeValue());
        verify(bookService, times(1)).findById(1L);
        verify(bookService, times(0)).deleteById(1L);
    }
}
