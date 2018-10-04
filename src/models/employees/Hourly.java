package models.employees;

import models.employees.info.Address;
import models.employees.info.Syndicate;
import models.employees.info.Payment;
import models.payday.Weekly;
import models.employees.info.WorkedTime;

public class Hourly extends Employee {

    private Payment payment_schedule;

    public Hourly(int id, String name, Address address, Syndicate syndicate, double income, String type, WorkedTime worked_hours){
        super(id, name, address, syndicate, worked_hours);
        this.payment_schedule = new Payment(new Weekly(1,5), income, type);
    }

    @Override
    public Payment getPayment_schedule() {
        return payment_schedule;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Employee type: Hourly\n" +
                "Income: $" + payment_schedule.getSalary() + " per hour\n" +
                "Payment method: " + payment_schedule.getType() + "\n";
    }
}
