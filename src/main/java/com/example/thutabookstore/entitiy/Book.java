package com.example.thutabookstore.entitiy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Book extends IdClass {
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "name_published")
    private LocalDate yearPublished;
    private String publisher;
    private double price;
    private int quantity;
    private String genre;
    private String imageUrl;
    private String description;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Author author;
}
