package com.anime.SpringWebRestAPI.controllers;

import com.anime.SpringWebRestAPI.dto.EmployeeDTO;
import com.anime.SpringWebRestAPI.exceptions.ResourceNotFoundException;
import com.anime.SpringWebRestAPI.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;


@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService= employeeService;
    }

    @GetMapping("/{empId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "empId") Long id) {
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
        return employeeDTO.map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElseThrow(()->new ResourceNotFoundException("Employee not found with ID "+id));
    }
//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<String>handleExceptionNotFound(NoSuchElementException exception){
//        return new ResponseEntity<>("Employee Not Found",HttpStatus.NOT_FOUND);
//    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(
            @RequestParam(required = false) String age,
            @RequestParam(required = false) String sortBy) {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody @Valid EmployeeDTO inputEmp) {
        EmployeeDTO employeeDTO= employeeService.addEmployee(inputEmp);
        return new ResponseEntity<>(employeeDTO, HttpStatus.CREATED);
    }
    @PutMapping("/{empId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid EmployeeDTO inputEmp,@PathVariable(name="empId")Long id){
        return ResponseEntity.ok(employeeService.updateEmployeeById(inputEmp,id));
    }
    @DeleteMapping("/{empId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable(name = "empId") Long id) {
//        return ResponseEntity.ok(employeeService.deleteEmployeeById(id)); // Optional: Add null check or response entity.
        boolean gotDeleted=employeeService.deleteEmployeeById(id);
        if(gotDeleted)return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{empId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String,Object> updates, @PathVariable(name="empId")Long id){
        EmployeeDTO employeeDTO= employeeService.updatePartialEmployeeById(updates,id);
        if(employeeDTO==null)return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }
}

