package com.anime.SpringWebRestAPI.controllers;

import com.anime.SpringWebRestAPI.dto.EmployeeDTO;
import com.anime.SpringWebRestAPI.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService= employeeService;
    }

    @GetMapping("/{empId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "empId") Long id) {
        return employeeService.getEmployeeById(id); // Fixed syntax for `findById`
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees(
            @RequestParam(required = false) String age,
            @RequestParam(required = false) String sortBy) {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeDTO addEmployee(@RequestBody EmployeeDTO inputEmp) {
        return employeeService.addEmployee(inputEmp);
    }
}

