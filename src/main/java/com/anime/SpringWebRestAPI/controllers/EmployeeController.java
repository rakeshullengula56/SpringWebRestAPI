package com.anime.SpringWebRestAPI.controllers;

import com.anime.SpringWebRestAPI.dto.EmployeeDTO;
import com.anime.SpringWebRestAPI.entities.EmployeeEntity;
import com.anime.SpringWebRestAPI.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/{empId}")
    public EmployeeEntity getEmployeeById(@PathVariable(name = "empId") Long id) {
        return employeeRepository.findById(id).orElse(null); // Fixed syntax for `findById`
    }

    @GetMapping
    public List<EmployeeEntity> getAllEmployees(
            @RequestParam(required = false) String age,
            @RequestParam(required = false) String sortBy) {
        return employeeRepository.findAll();
    }

    @PostMapping
    public EmployeeEntity addEmployee(@RequestBody EmployeeEntity inputEmp) {
        return employeeRepository.save(inputEmp);
    }
}

