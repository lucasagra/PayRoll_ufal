package models.employees;


import models.employees.info.Address;
import models.employees.info.Syndicate;
import models.employees.info.payment.Payment;


public class Employee {
    private int id;
    private String name;
    private Address address;
    private Syndicate sindicate;
    private Payment payment_schedule;

    public Employee(int id, String name, Address address, Syndicate sindicate) {
        this.name = name;
        this.address = address;
        this.id = id;
    }
}
