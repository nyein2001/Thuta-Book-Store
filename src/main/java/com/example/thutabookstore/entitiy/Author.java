package com.example.thutabookstore.entitiy;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
public class Author extends IdClass {

    @NotBlank(message = "Name cannot be blank")
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "author")
    private List<Book> bookList = new ArrayList<>();

    public void addBook(Book book) {
        book.setAuthor(this);
        bookList.add(book);
    }

}
