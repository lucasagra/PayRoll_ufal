package models.employees;


import models.employees.info.Address;
import models.employees.info.Syndicate;

import java.io.Serializable;


public class Employee implements Serializable {
    private int id;
    private String name;
    private Address address;
    private Syndicate syndicate;

    public Employee(int id, String name, Address address, Syndicate syndicate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.syndicate = syndicate;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public Syndicate getSyndicate() {
        return syndicate;
    }

}
