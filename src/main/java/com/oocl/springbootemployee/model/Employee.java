package com.oocl.springbootemployee.model;

import jakarta.persistence.*;


@Entity
public class Employee {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String name;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Double salary;
    private Boolean isActive = true;
    private Integer companyId;


    public Employee(Integer id, String name, Integer age, Gender gender, Double salary) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    public Employee() {
    }

    public Employee(String janeJohnson, Integer age, Gender gender, double salary) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }


    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public Double getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public void setId(Long id) {
        this.id = Math.toIntExact(id);
    }

    public Integer getId() {
        return id;
    }

    public boolean isActive() {
        return isActive;
    }
}
