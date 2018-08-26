package models.employees;

import models.employees.info.Address;
import models.employees.info.Syndicate;
import models.employees.info.payment.Payment;
import models.payday.MonthlyPayday;

public class Salaried extends Employee {

    private Payment payment_schedule;

    public Salaried(int id, String name, Address address, Syndicate syndicate, double salary){
        super(id, name, address, syndicate);
        this.payment_schedule = new Payment(new MonthlyPayday("1"), salary);
    }

    public Salaried(int id, String name, Address address, Syndicate syndicate, Payment payment_schedule){
        super(id, name, address, syndicate);
        this.payment_schedule = payment_schedule;
    }

    public Payment getPayment_schedule() {
        return payment_schedule;
    }
}
