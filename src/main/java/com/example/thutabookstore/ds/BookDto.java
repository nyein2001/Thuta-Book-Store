package com.example.thutabookstore.ds;

import com.example.thutabookstore.entitiy.Author;
import com.example.thutabookstore.entitiy.Category;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BookDto {

    private int id;
    private String title;
    private LocalDate yearPublished;
    private String publisher;
    private double price;
    private int quantity;
    private String genre;
    private String imageUrl;
    private String description;
    private Category category;
    private Author author;

    public BookDto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookDto bookDto)) return false;
        return getId() == bookDto.getId() && Double.compare(bookDto.getPrice(), getPrice()) == 0 && getQuantity() == bookDto.getQuantity() && getTitle().equals(bookDto.getTitle()) && getYearPublished().equals(bookDto.getYearPublished()) && getPublisher().equals(bookDto.getPublisher()) && getGenre().equals(bookDto.getGenre()) && getImageUrl().equals(bookDto.getImageUrl()) && getDescription().equals(bookDto.getDescription()) && getCategory().equals(bookDto.getCategory()) && getAuthor().equals(bookDto.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getYearPublished(), getPublisher(), getPrice(), getQuantity(), getGenre(), getImageUrl(), getDescription(), getCategory(), getAuthor());
    }
}
