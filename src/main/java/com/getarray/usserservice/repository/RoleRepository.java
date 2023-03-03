package com.getarray.usserservice.repository;

import com.getarray.usserservice.domain.AppRole;

import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByName(String name);
}
