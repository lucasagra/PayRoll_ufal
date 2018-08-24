package models.Employees;

import models.Employees.Info.Address;
import models.Employees.Info.Sindicate;

public class Hourly extends Employee {

    public Hourly(int id, String name, Address address, Sindicate sindicate){
        super(id, name, address, sindicate);
    }
}
