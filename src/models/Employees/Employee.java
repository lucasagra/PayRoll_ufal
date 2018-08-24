package models.Employees;
import models.Employees.Info.Address;
import models.Employees.Info.Sindicate;

public class Employee {
    private int id;
    private String name;
    private Address address;
    private Sindicate sindicate;

    public Employee(int id, String name, Address address, Sindicate sindicate) {
        this.name = name;
        this.address = address;
        this.id = id;
    }
}
