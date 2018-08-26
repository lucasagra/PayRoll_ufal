package models.employees.info.payment;

import models.payday.Payday;

public class Personally extends Payment {

    public Personally(Payday payday, double amount){
        super(payday, amount);
    }
}
