package com.anime.SpringWebRestAPI.services;

import com.anime.SpringWebRestAPI.dto.EmployeeDTO;
import com.anime.SpringWebRestAPI.entities.EmployeeEntity;
import com.anime.SpringWebRestAPI.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO getEmployeeById(Long id) {
        EmployeeEntity employeeEntity=employeeRepository.findById(id).orElse(null);
        return modelMapper.map(employeeEntity,EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAllEmployees() {
       List<EmployeeEntity> employeeEntities= employeeRepository.findAll();
       return employeeEntities
               .stream()
               .map(EmployeeEntity->modelMapper.map(EmployeeEntity,EmployeeDTO.class))
               .collect(Collectors.toList());
    }

    public EmployeeDTO addEmployee(EmployeeDTO inputEmp) {
        EmployeeEntity toSaveEmployee=modelMapper.map(inputEmp,EmployeeEntity.class);
        EmployeeEntity savedEmployee=employeeRepository.save(toSaveEmployee);

        return modelMapper.map(savedEmployee,EmployeeDTO.class);
    }
}
