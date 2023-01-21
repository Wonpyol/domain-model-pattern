package com.example.domain.item;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("album")
@SuperBuilder
@Getter
public class Album extends Item {
    public Album() {}
    private String artist;
    private String etc;
}
