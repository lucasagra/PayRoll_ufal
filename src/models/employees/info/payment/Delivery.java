package models.employees.info.payment;

import models.payday.Payday;

public class Delivery extends Payment{

    public Delivery(Payday payday, double amount){
        super(payday, amount);
    }
}
