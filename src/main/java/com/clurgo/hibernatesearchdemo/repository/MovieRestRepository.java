package com.clurgo.hibernatesearchdemo.repository;

import com.clurgo.hibernatesearchdemo.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(path = "movie")
public interface MovieRestRepository extends JpaRepository<Movie, UUID> {


}
