package com.anime.SpringWebRestAPI.services;

import com.anime.SpringWebRestAPI.dto.EmployeeDTO;
import com.anime.SpringWebRestAPI.entities.EmployeeEntity;
import com.anime.SpringWebRestAPI.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

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
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        if (employeeEntity == null) {
            return null; // Or throw a custom exception
        }
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
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

    public EmployeeDTO updateEmployeeById(EmployeeDTO inputEmp, Long id) {
        if (!employeeRepository.existsById(id)) {
            return null; // Or throw a custom exception
        }
        EmployeeEntity toSaveEntity = modelMapper.map(inputEmp, EmployeeEntity.class);
        toSaveEntity.setId(id);
        EmployeeEntity savedEntity = employeeRepository.save(toSaveEntity);
        return modelMapper.map(savedEntity, EmployeeDTO.class);
    }


    public boolean deleteEmployeeById(Long id) {
        boolean exist=employeeRepository.existsById(id);
        if(!exist)return false;
        employeeRepository.deleteById(id);
        return true;

    }

    public EmployeeDTO updatePartialEmployeeById(Map<String, Object> updates, Long id) {
        boolean exists=employeeRepository.existsById(id);
        if(!exists)return null;
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
