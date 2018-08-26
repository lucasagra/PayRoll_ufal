package models.employees.info.payment;

import models.payday.Payday;

import java.io.Serializable;

public class Payment implements Serializable {
    private Payday payday;
    private double salary;

    public Payment(Payday payday, double salary){
        this.payday = payday;
        this.salary = salary;
    }
}
