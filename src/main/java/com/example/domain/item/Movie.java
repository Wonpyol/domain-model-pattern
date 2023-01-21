package com.example.domain.item;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("movie")
@Getter
@SuperBuilder
public class Movie extends Item{
    public Movie() {}
    private String director;
    private String actor;
}
