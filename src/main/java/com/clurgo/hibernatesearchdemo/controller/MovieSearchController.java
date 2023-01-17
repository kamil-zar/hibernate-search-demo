package com.clurgo.hibernatesearchdemo.controller;

import com.clurgo.hibernatesearchdemo.dto.MovieDto;
import com.clurgo.hibernatesearchdemo.service.MovieSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movie-search")
@RequiredArgsConstructor
public class MovieSearchController {

    private final MovieSearchService movieSearchService;

    @PostMapping("/rebuild-index")
    public void rebuildIndex() {
        movieSearchService.rebuildIndex();
    }

    @GetMapping
    public List<MovieDto> search(@RequestParam String query, @RequestParam(required = false) Integer score) {
        return movieSearchService.search(query, score);
    }
}
