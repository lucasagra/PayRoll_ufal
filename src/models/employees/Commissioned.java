package models.employees;

import models.Sale;
import models.employees.info.*;

import java.util.ArrayList;
import java.util.List;

public class Commissioned extends Employee {

    private List<Sale> sales = new ArrayList<>();

    public Commissioned(int id, String name, Address address, Syndicate sindicate){
        super(id, name, address, sindicate);
    }
}
