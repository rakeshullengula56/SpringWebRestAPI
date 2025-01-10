package com.anime.SpringWebRestAPI.dto;

import com.anime.SpringWebRestAPI.annotations.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    @Positive(message = "ID must be a positive number")
    private Long id;
    @NotBlank(message = "Name of Employee cannot be Null")
    @Size(min = 3,max=10,message = "Name should be between 3-10 characters length")
    private String name;
    @NotBlank(message = "Email of Employee cannot be Null")
    @Email(message = "Email should be valid")
    private String email;
    @Max(value = 80,message = "Age cannot be greater than 80")
    @Min(value = 18,message = "Age cannot be less than 18")
    private Integer age;
    @NotBlank(message = "Role of Employee cannot be Null")
    @EmployeeRoleValidation
    private String role;
    @NotNull(message = "Salary of Employee cannot be Null")
    @Digits(integer = 7, fraction = 2, message = "Salary must have up to 7 digits and 2 decimal places.")
    @DecimalMin(value = "1000.99", message = "Salary must be at least 1000.99")
    @DecimalMax(value = "100000.99", message = "Salary cannot exceed 100000.99")
    private Double salary;
    @NotNull(message = "Date of joining cannot be null")
    @PastOrPresent(message = "Date of joining of Employee cannot be in the future")
    private LocalDate dateOfJoining;
    @AssertTrue(message = "Employee should be active")
    @JsonProperty("isActive")
    private Boolean isActive;
}
