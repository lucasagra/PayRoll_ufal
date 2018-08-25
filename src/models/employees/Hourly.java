package models.employees;

import models.employees.info.Address;
import models.employees.info.Syndicate;

public class Hourly extends Employee {

    public Hourly(int id, String name, Address address, Syndicate sindicate){
        super(id, name, address, sindicate);
    }
}
