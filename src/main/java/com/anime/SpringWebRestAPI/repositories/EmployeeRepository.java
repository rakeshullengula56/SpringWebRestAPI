package com.anime.SpringWebRestAPI.repositories;

import com.anime.SpringWebRestAPI.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.ScopedValue;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
    ScopedValue<Object> findAllById(Long id);
}
