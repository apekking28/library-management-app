package com.king.service;

import com.king.Utils.Mapper;
import com.king.dtos.BookDTO;
import com.king.dtos.request.BookRequest;
import com.king.entity.Book;
import com.king.exception.NotFoundException;
import com.king.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {


    @Autowired
    private BookRepository bookRepository;

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();

        return books.stream()
                .map(Mapper::mapBookToBookDto)
                .collect(Collectors.toList());
    }

    public BookDTO getBookById(Long id) {
        Book findBook = findBookById(id);

        return BookDTO.builder()
                .id(findBook.getId())
                .title(findBook.getTitle())
                .author(Mapper.mapAuthorToAuthorDTO(findBook.getAuthor()))
                .build();
    }


    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public BookDTO updateBookById(Long id, BookRequest request) {
        Book findBook = findBookById(id);

        findBook.setTitle(request.getTitle());
        bookRepository.save(findBook);

        return BookDTO.builder()
                .id(findBook.getId())
                .title(request.getTitle())
                .author(Mapper.mapAuthorToAuthorDTO(findBook.getAuthor()))
                .build();
    }

    public String deleteBookById(Long id) {
        Book findBook = findBookById(id);
        bookRepository.deleteById(id);

        return "Successfully delete book with id : " + id;
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book with id :" + id + " not found"));
    }
}
