package com.anime.SpringWebRestAPI.controllers;

import com.anime.SpringWebRestAPI.entities.DepartmentEntity;
import com.anime.SpringWebRestAPI.services.DepartmentService;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping(path="/assignedDepartment/{empId}")
    public DepartmentEntity assignedDepartmentOfManager(@PathVariable(name = "empId")Long empId){
        return departmentService.assignedDepartmentOfManager(empId);
    }
    @GetMapping(path = "/{deptId}")
    public DepartmentEntity getDepartmentEntityById(@PathVariable(name = "deptId")Long deptId){
        return departmentService.getDepartmentEntityById(deptId);
    }
    @PostMapping
    public DepartmentEntity createNewDept(@RequestBody DepartmentEntity departmentEntity){
        return departmentService.createNewDept(departmentEntity);
    }
    @PutMapping(path = "/{deptId}/manager/{empId}")
    public DepartmentEntity assignManagerToDept(@PathVariable(name = "deptId")Long id,@PathVariable(name = "empId")Long empId){
        return departmentService.assignManagerToDept(id,empId);
    }

}
