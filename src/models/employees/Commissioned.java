package models.employees;

import models.Sale;
import models.employees.info.*;
import models.employees.info.payment.Payment;
import models.payday.WeeklyPayday;

import java.util.ArrayList;
import java.util.List;

public class Commissioned extends Salaried {

    private List<Sale> sales = new ArrayList<>();
    private Payment payment_schedule;
    private int commission_percent;

    public Commissioned(int id, String name, Address address, Syndicate syndicate, double salary, int commission){
        super(id, name, address, syndicate, salary);
        this.payment_schedule = new Payment(new WeeklyPayday(2,5), salary);
        this.commission_percent = commission;
    }


    public Commissioned(int id, String name, Address address, Syndicate syndicate, int commission, Payment payment_schedule, List<Sale> sales){
        super(id, name, address, syndicate, payment_schedule);
        this.payment_schedule = payment_schedule;
        this.commission_percent = commission;
        this.sales = sales;
    }

    public List<Sale> getSales() {
        return sales;
    }

    @Override
    public Payment getPayment_schedule() {
        return payment_schedule;
    }

    public int getCommission_percent() {
        return commission_percent;
    }
}
