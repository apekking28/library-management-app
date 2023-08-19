package com.king.Utils;

import com.king.dtos.AuthorDTO;
import com.king.dtos.BookDTO;
import com.king.entity.Author;
import com.king.entity.Book;

public class Mapper {
    public static   BookDTO mapToDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(mapToDTO(book.getAuthor()));
        return dto;
    }

    public static AuthorDTO mapToDTO(Author author) {
        AuthorDTO dto = new AuthorDTO();
        dto.setId(author.getId());
        dto.setName(author.getName());
        return dto;
    }

}
