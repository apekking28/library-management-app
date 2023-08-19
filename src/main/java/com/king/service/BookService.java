package com.king.service;

import com.king.Utils.Mapper;
import com.king.dtos.BookDTO;
import com.king.entity.Book;
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
                .map(Mapper::mapToDTO)
                .collect(Collectors.toList());
    }

    public Book getBoookById (Long id) {
         Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Buku dengan id :" + id + " tidak ditemukan"));


        return book;
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }
}
