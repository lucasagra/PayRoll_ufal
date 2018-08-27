package models.employees;

import models.employees.info.Address;
import models.employees.info.Syndicate;
import models.employees.info.Payment;
import models.payday.WeeklyPayday;
import models.employees.info.WorkedTime;

public class Hourly extends Employee {

    private Payment payment_schedule;

    public Hourly(int id, String name, Address address, Syndicate syndicate, double income, String type, WorkedTime worked_hours){
        super(id, name, address, syndicate, worked_hours);
        this.payment_schedule = new Payment(new WeeklyPayday(1,6), income, type);
    }

    public Hourly(int id, String name, Address address, Syndicate syndicate, Payment payment_schedule, WorkedTime worked_hours){
        super(id, name, address, syndicate, worked_hours);
        this.payment_schedule = payment_schedule;
    }

    public Payment getPayment_schedule() {
        return payment_schedule;
    }
}
