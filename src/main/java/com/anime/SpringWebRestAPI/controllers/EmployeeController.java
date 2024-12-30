package com.anime.SpringWebRestAPI.controllers;

import com.anime.SpringWebRestAPI.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class EmployeeController {
    @GetMapping("/employees/{empId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long empId){
        return new EmployeeDTO(empId,"Rakesh","ram@gmail.com",22, LocalDate.of(2024,12,30),true);
    }
}
