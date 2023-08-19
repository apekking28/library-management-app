package com.king.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.Set;

import static org.hibernate.annotations.FetchMode.SELECT;

@Entity
@Getter
@Setter
@Table(name = "library_address")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;
    private String zipCode;

    @JsonIgnore
    @OneToMany(mappedBy = "address")
    private Set<User> users;

    @JsonIgnore
    @OneToMany(mappedBy = "address")
    private Set<Author> authors;

}
