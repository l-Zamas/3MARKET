package com.unipacifico._3market.repository;

import com.unipacifico._3market.model.Role;
import com.unipacifico._3market.model.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository <Role, Long> {

    Optional<Role> findByName(RoleType name);
}
