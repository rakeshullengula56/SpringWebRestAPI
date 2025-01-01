package com.anime.SpringWebRestAPI.controllers;

import com.anime.SpringWebRestAPI.dto.EmployeeDTO;
import com.anime.SpringWebRestAPI.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService= employeeService;
    }

    @GetMapping("/{empId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "empId") Long id) {
        EmployeeDTO employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return null; // Or throw a custom exception
        }
        return employee;
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
    @PutMapping("/{empId}")
    public EmployeeDTO updateEmployeeById(@RequestBody EmployeeDTO inputEmp,@PathVariable(name="empId")Long id){
        return employeeService.updateEmployeeById(inputEmp,id);
    }
    @DeleteMapping("/{empId}")
    public boolean deleteEmployeeById(@PathVariable(name = "empId") Long id) {
        return employeeService.deleteEmployeeById(id); // Optional: Add null check or response entity.
    }

    @PatchMapping("/{empId}")
    public EmployeeDTO updatePartialEmployeeById(@RequestBody Map<String,Object> updates, @PathVariable(name="empId")Long id){
        return employeeService.updatePartialEmployeeById(updates,id);
    }
}

