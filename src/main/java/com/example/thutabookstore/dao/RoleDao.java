package com.example.thutabookstore.dao;

import com.example.thutabookstore.entitiy.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleDao extends JpaRepository<Role, Integer> {
    Optional<Role> findRoleByRoleName(String roleName);
}
