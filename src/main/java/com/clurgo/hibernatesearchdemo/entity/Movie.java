package com.clurgo.hibernatesearchdemo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
// IMPORT!!! Import should be form hibernate search!
@Indexed
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @FullTextField
    private String title;

    @FullTextField
    private String description;

    @GenericField
    private Integer score;

    @GenericField
    private LocalDate premiere;

    //Requires inverse mapping!
    @IndexedEmbedded
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
           name = "movie_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<Actor> actors;



}
