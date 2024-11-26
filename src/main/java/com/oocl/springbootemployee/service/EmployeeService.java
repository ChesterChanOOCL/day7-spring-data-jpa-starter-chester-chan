package com.oocl.springbootemployee.service;

import com.oocl.springbootemployee.exception.EmployeeAgeNotValidException;
import com.oocl.springbootemployee.exception.EmployeeAgeSalaryNotMatchedException;
import com.oocl.springbootemployee.exception.EmployeeInactiveException;
import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.model.Gender;
import com.oocl.springbootemployee.repository.EmployeeInMemoryRepository;
import java.util.List;
import java.util.Optional;

import com.oocl.springbootemployee.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
@Service
public class EmployeeService {
    private final EmployeeInMemoryRepository employeeInMemoryRepository;
    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeInMemoryRepository employeeInMemoryRepository, EmployeeRepository employeeRepository) {
        this.employeeInMemoryRepository = employeeInMemoryRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public List<Employee> findAll(Gender gender) {
        return employeeInMemoryRepository.findAllByGender(gender);
    }

    public List<Employee> findAll(Integer page, Integer pageSize) {
        final Page<Employee> pageResult = employeeRepository.findAll(PageRequest.of(page, pageSize));
        return pageResult.getContent();
    }

    public Employee findById(Integer employeeId) {

        return employeeInMemoryRepository.findById(employeeId);
    }

    public Employee create(Employee employee) {
        if(employee.getAge() < 18 || employee.getAge() > 65)
            throw new EmployeeAgeNotValidException();
        if(employee.getAge() >= 30 && employee.getSalary() < 20000.0)
            throw new EmployeeAgeSalaryNotMatchedException();

        employee.setActive(true);
        return employeeRepository.save(employee);
    }

    public Employee update(int id, Employee employee) throws EmployeeInactiveException {
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if (existingEmployee.isPresent()) {
            Employee emp = existingEmployee.get();
            if (!emp.isActive()) {
                throw new EmployeeInactiveException("Employee is inactive");
            }
            // Update logic here
            return employeeRepository.save(employee);
        } else {
            throw new EmployeeInactiveException("Employee not found");
        }
    }

    public void delete(Integer employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
