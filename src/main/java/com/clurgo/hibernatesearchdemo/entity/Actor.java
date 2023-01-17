package com.clurgo.hibernatesearchdemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @FullTextField
    private String firstName;

    @FullTextField
    private String lastName;

    //Required by hibernate search.
    @ManyToMany(mappedBy = "actors")
    private Set<Movie> movies;
}
