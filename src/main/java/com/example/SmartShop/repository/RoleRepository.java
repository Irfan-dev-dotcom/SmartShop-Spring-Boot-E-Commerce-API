package com.example.SmartShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.management.relation.Role;
import java.util.Optional;

public interface RoleRepository extends JpaRepository {
    Optional<Role> findByName(String name);
}
