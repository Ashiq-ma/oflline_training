package com.student.ust.service;

import com.student.ust.entity.Book;
import com.student.ust.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * The type Book service.
 */
@Service
public class BookService {
    /**
     * The Book repository.
     */
    @Autowired
    BookRepository bookRepository;

    /**
     * Save book.
     *
     * @param book the book
     */
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    /**
     * Gets book by id.
     *
     * @param bookId the book id
     * @return the book by id
     */
    public Book getBookById(int bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }


    /**
     * Gets book all.
     *
     * @return the book all
     */
    public List<Book> getBookAll() {
        return bookRepository.findAll();
    }

    /**
     * Delete book.
     *
     * @param bookId the book id
     */
    public void deleteBook(Integer bookId) {
        bookRepository.deleteById(bookId);

    }

    /**
     * Update book book.
     *
     * @param book the book
     * @return the book
     */
    public Book updateBook(Book book) {
        Book updateBook=bookRepository.findById(book.getBookId()).orElseThrow(()->new NoSuchElementException());
        updateBook.setBookAuthorName(book.getBookAuthorName());
        updateBook.setBookIsp(book.getBookIsp());
        updateBook.setBookName(book.getBookName());
        bookRepository.save(updateBook);
        return updateBook;
    }
    }
