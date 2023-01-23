package com.example.thutabookstore.dao;

import com.example.thutabookstore.entitiy.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, Integer> {
}
