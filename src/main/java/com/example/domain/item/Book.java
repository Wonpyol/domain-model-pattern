package com.example.domain.item;

import com.example.domain.Category;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;


@Entity
@Getter
@DiscriminatorValue("book")
@SuperBuilder
public class Book extends Item {
    private String author;
    private String isbn;

    public Book() {
    }
}
