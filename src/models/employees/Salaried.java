package models.employees;

import models.employees.info.Address;
import models.employees.info.Syndicate;
import models.employees.info.Payment;
import models.payday.MonthlyPayday;
import models.employees.info.WorkedTime;

public class Salaried extends Employee {

    private Payment payment_schedule;

    public Salaried(int id, String name, Address address, Syndicate syndicate, double salary, String type, WorkedTime worked_hours){
        super(id, name, address, syndicate, worked_hours);
        this.payment_schedule = new Payment(new MonthlyPayday("1"), salary, type);
    }

    public Salaried(int id, String name, Address address, Syndicate syndicate, Payment payment_schedule, WorkedTime worked_hours){
        super(id, name, address, syndicate, worked_hours);
        this.payment_schedule = payment_schedule;
    }

    public Payment getPayment_schedule() {
        return payment_schedule;
    }
}
