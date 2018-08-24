package models.Employees;

import models.Employees.Info.Address;
import models.Employees.Info.Sindicate;

public class Commissioned extends Employee {
    public Commissioned(int id, String name, Address address, Sindicate sindicate){
        super(id, name, address, sindicate);
    }
}
