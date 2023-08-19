package com.king.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AuthorDTO {

    private Long id;
    private String name;
    private List<BookDTO> books;

    // Constructors, getters, setters
}
