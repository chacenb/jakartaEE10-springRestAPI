package com.example.demo;

import jakarta.persistence.*;

@Entity
@Table(schema = "demojpaappschema1")
public class Employee {

    public Employee() {
    }

    @Id
    @Column(nullable = false)
    @GeneratedValue
    private Long id;

    @Column(name = "fname")
    private String firstName;

    @Column(name = "lname")
    private String lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
