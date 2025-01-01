package com.anime.SpringWebRestAPI.repositories;

import com.anime.SpringWebRestAPI.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {

}
