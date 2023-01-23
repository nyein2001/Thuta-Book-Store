package com.example.thutabookstore.dao;

import com.example.thutabookstore.entitiy.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorDao extends JpaRepository<Author, Integer> {
}
