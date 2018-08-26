package models.employees.info.payment;

import models.payday.Payday;

public class Deposit extends Payment {

    public Deposit(Payday payday, double amount){
        super(payday, amount);
    }
}
