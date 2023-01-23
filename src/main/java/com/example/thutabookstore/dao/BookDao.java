package com.example.thutabookstore.dao;

import com.example.thutabookstore.entitiy.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<Book, Integer> {
}
