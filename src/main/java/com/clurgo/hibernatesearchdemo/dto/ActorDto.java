package com.clurgo.hibernatesearchdemo.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class ActorDto {

    UUID id;
    String firstName;
    String lastName;
}
