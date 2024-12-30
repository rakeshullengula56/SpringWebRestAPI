package com.anime.SpringWebRestAPI.controllers;

import com.anime.SpringWebRestAPI.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path="/employees")
public class EmployeeController {
    @GetMapping("/{empId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name="empId") Long Id){
        return new EmployeeDTO(Id,"Rakesh","ram@gmail.com",22, LocalDate.of(2024,12,30),true);
    }
    @GetMapping
    public String getAllEmployees(@RequestParam(required = false) String age,@RequestParam(required = false) String sortBy){
        return "Hi all "+age+ " "+sortBy;
    }
}
