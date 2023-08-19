package com.king.service;

import com.king.entity.Address;
import com.king.entity.Author;
import com.king.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AddressService addressService;

    public Author createAuthor(Author author) {
        Address address = addressService.getAddressById(author.getAddress().getId());

        Author saveAuthor = Author.builder()
                .name(author.getName())
                .address(address)
                .build();


        return authorRepository.save(saveAuthor);
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Author dengan id : " + id + " tidak ditemukan"));
    }

//    public List<Author> getAllAuthors() {
//        List<Author> authors = authorRepository.findAll();
//        authors.forEach(author -> {
//            author.getBooks().size(); // Ini akan memuat buku-buku terkait
//        });
//        return authors;
//    }

    public Author updateAuthor(Long id, Author author) {
        Author findAuthor = getAuthorById(id);

        Author updateAuthor = Author.builder()
                .id(findAuthor.getId())
                .name(author.getName())
                .address(findAuthor.getAddress())
                .build();

        return authorRepository.save(updateAuthor);
    }

    public void deleteAuthor(Long id) {
        Author findAuthor = getAuthorById(id);
        authorRepository.deleteById(id);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
}
