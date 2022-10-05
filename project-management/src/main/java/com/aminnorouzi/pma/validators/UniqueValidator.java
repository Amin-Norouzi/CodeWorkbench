package com.aminnorouzi.pma.validators;

import com.aminnorouzi.pma.dao.EmployeeRepository;
import com.aminnorouzi.pma.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<UniqueValue, String> {

    @Autowired
    EmployeeRepository empRepo;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        System.out.println("Entered validation method..");

        Employee emp = empRepo.findByEmail(value);

        if (emp != null)
            return false;
        else
            return true;
    }
}