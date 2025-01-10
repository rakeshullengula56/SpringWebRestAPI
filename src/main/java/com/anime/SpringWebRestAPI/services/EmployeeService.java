package com.anime.SpringWebRestAPI.services;

import com.anime.SpringWebRestAPI.dto.EmployeeDTO;
import com.anime.SpringWebRestAPI.entities.EmployeeEntity;
import com.anime.SpringWebRestAPI.exceptions.ResourceNotFoundException;
import com.anime.SpringWebRestAPI.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
//        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
//        return modelMapper.map(employeeEntity, EmployeeDTO.class);
        return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class));
    }


    public List<EmployeeDTO> getAllEmployees() {
       List<EmployeeEntity> employeeEntities= employeeRepository.findAll();
       return employeeEntities
               .stream()
               .map(EmployeeEntity->modelMapper.map(EmployeeEntity,EmployeeDTO.class))
               .collect(Collectors.toList());
    }
    public void isExistsById(Long id){
        boolean exists=employeeRepository.existsById(id);
        if(!exists) throw new ResourceNotFoundException("Employee not found with ID "+id);
    }
    public EmployeeDTO addEmployee(EmployeeDTO inputEmp) {
        EmployeeEntity toSaveEmployee=modelMapper.map(inputEmp,EmployeeEntity.class);
        EmployeeEntity savedEmployee=employeeRepository.save(toSaveEmployee);

        return modelMapper.map(savedEmployee,EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById(EmployeeDTO inputEmp, Long id) {
        isExistsById(id);
        EmployeeEntity toSaveEntity = modelMapper.map(inputEmp, EmployeeEntity.class);
        toSaveEntity.setId(id);
        EmployeeEntity savedEntity = employeeRepository.save(toSaveEntity);
        return modelMapper.map(savedEntity, EmployeeDTO.class);
    }


    public boolean deleteEmployeeById(Long id) {
        isExistsById(id);
        employeeRepository.deleteById(id);
        return true;

    }

    public EmployeeDTO updatePartialEmployeeById(Map<String, Object> updates, Long id) {
        isExistsById(id);
        EmployeeEntity employeeEntity=employeeRepository.findById(id).get();
        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class, field);
            if (fieldToBeUpdated != null) {
                fieldToBeUpdated.setAccessible(true);
                ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
            }
        });

        employeeRepository.save(employeeEntity);
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }
}
