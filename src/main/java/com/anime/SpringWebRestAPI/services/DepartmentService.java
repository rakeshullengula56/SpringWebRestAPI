package com.anime.SpringWebRestAPI.services;

import com.anime.SpringWebRestAPI.entities.DepartmentEntity;
import com.anime.SpringWebRestAPI.entities.EmployeeEntity;
import com.anime.SpringWebRestAPI.repositories.DepartmentRepository;
import com.anime.SpringWebRestAPI.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;


    public DepartmentService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public DepartmentEntity createNewDept(DepartmentEntity departmentEntity){
        return departmentRepository.save(departmentEntity);
    }
    public DepartmentEntity getDepartmentEntityById(Long id){
        return departmentRepository.findById(id).orElse(null);
    }

    public DepartmentEntity assignManagerToDept(Long deptId, Long empId) {
        Optional<DepartmentEntity> departmentEntity=departmentRepository.findById(deptId);
        Optional<EmployeeEntity>employeeEntity=employeeRepository.findById(empId);
        return departmentEntity.flatMap(department->employeeEntity.map(employee-> {
            department.setManager(employee);
            return departmentRepository.save(department);
        })).orElse(null);
    }

    public DepartmentEntity assignedDepartmentOfManager(Long empId) {
        //It is one way of doing here we find employee with id and find managingDepartment Having same id
//        Optional<EmployeeEntity>employeeEntity=employeeRepository.findById(empId);
//        return employeeEntity.map(employee->
//        {
//            return employee.getManagingDepartment();}
//        ).orElse(null);
        //Second way
        EmployeeEntity employeeEntity=EmployeeEntity.builder().id(empId).build();
        return departmentRepository.findByManager(employeeEntity);
    }
}
