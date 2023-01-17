package com.clurgo.hibernatesearchdemo.service;

import com.clurgo.hibernatesearchdemo.dto.ActorDto;
import com.clurgo.hibernatesearchdemo.dto.MovieDto;
import com.clurgo.hibernatesearchdemo.entity.Actor;
import com.clurgo.hibernatesearchdemo.entity.Movie;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieSearchService {

    private final EntityManager entityManager;

    public void rebuildIndex() {
        log.info("Rebuilding HS index.");
        SearchSession searchSession = Search.session(entityManager);

        try {
            searchSession.massIndexer(Movie.class)
                    .threadsToLoadObjects(4)
                    .startAndWait();

            log.info("Index rebuild finished.");
        } catch (InterruptedException e) {
            throw new IllegalStateException("Failed to rebuild index.", e);
        }


    }

    public List<MovieDto> search(String query, Integer score) {
        SearchSession searchSession = Search.session(entityManager);

        // List<Movie> hits = searchSession.search(Movie.class)
        //         .where(f -> f.match()
        //                 .fields("title", "description", "actors.firstName", "actors.lastName")
        //                 .matching(query)
        //                 .fuzzy())
        //         .fetchAll()
        //         .hits();

        List<Movie> hits = searchSession.search(Movie.class)
                .where(f -> f.bool(b -> {
                    //must = AND
                    //should = OR
                    b.must(f.match()
                            .fields("title", "description", "actors.firstName", "actors.lastName")
                            .matching(query)
                            .fuzzy());

                    if (Objects.nonNull(score)) {
                        b.must(f.range()
                                .field("score")
                                .atLeast(score)
                        );
                    }

                }))
                .fetchAll()
                .hits();
        return hits.stream().map(this::toDto).toList();
    }

    private MovieDto toDto(Movie movie) {
        return MovieDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .score(movie.getScore())
                .premiere(movie.getPremiere())
                .actors(movie.getActors().stream()
                        .map(this::toDto)
                        .collect(Collectors.toUnmodifiableSet()))
                .build();
    }

    private ActorDto toDto(Actor actor) {
        return ActorDto.builder()
                .id(actor.getId())
                .firstName(actor.getFirstName())
                .lastName(actor.getLastName())
                .build();
    }

}
