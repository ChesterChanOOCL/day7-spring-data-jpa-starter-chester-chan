package com.oocl.springbootemployee.service;

import com.oocl.springbootemployee.exception.CompanyNotFoundException;
import com.oocl.springbootemployee.model.Company;
import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.repository.CompanyRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public List<Company> findAll(int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        return companyRepository.findAll(pageable).getContent();
    }

    public Company findById(Integer id) {
        return companyRepository.findById(id).orElse(null);
    }


    public List<Employee> getEmployeesByCompanyId(Integer id) {
        Company company = companyRepository.findById(id).orElse(null);
        return company.getEmployees();
    }

    public Company create(Company company) {
        return companyRepository.save(company);
    }

    public Company update(Integer id, Company company) {
        final var getCompany = companyRepository.findById(id).orElse(null);

        if (Objects.isNull(getCompany)) {
            throw new CompanyNotFoundException();
        }

        return companyRepository.save(company);
    }

    public void deleteCompany(Integer companyId) {
        companyRepository.deleteById(companyId);
    }
}