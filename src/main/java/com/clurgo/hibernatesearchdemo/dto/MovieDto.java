package com.clurgo.hibernatesearchdemo.dto;

import com.clurgo.hibernatesearchdemo.entity.Actor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Value
@Builder
public class MovieDto {

    UUID id;
    String title;
    String description;
    Integer score;
    LocalDate premiere;
    Set<ActorDto> actors;
}
