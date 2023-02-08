package com.example.thutabookstore.dao;

import com.example.thutabookstore.entitiy.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface CustomerDao extends JpaRepository<Customer, Integer> {
    Optional<Customer> findCustomerByUsername(String username);
}
