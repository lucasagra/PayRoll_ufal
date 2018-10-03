package models.employees;

import models.employees.info.Address;
import models.employees.info.Syndicate;
import models.employees.info.Payment;
import models.payday.Monthly;
import models.employees.info.WorkedTime;

public class Salaried extends Employee {

    private Payment payment_schedule;

    public Salaried(int id, String name, Address address, Syndicate syndicate, double salary, String type, WorkedTime worked_hours){
        super(id, name, address, syndicate, worked_hours);
        this.payment_schedule = new Payment(new Monthly("1"), salary, type);
    }

    @Override
    public Payment getPayment_schedule() {
        return payment_schedule;
    }

    @Override
    public String toString(){
        if (this instanceof Commissioned) return super.toString();
        else return super.toString() +
                "Employee type: Salaried \n" +
                payment_schedule.toString();
    }
}
