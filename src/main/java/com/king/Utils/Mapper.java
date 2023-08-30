package com.king.Utils;

import com.king.dtos.AuthorDTO;
import com.king.dtos.BookDTO;
import com.king.entity.Author;
import com.king.entity.Book;

public class Mapper {
    public static BookDTO mapBookToBookDto(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(mapAuthorToAuthorDTO(book.getAuthor()))
                .build();
    }

    public static AuthorDTO mapAuthorToAuthorDTO(Author author) {
        return AuthorDTO.builder()
                .id(author.getId())
                .name(author.getName())
                .build();
    }

}
