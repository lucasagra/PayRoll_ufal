package models;
import models.employees.info.WorkedTime;

import java.time.LocalDate;
import java.util.List;

public class PayCheck {
    private int employee_id;
    private LocalDate date;
    private WorkedTime worked_time;
    private String payment_type;
    private double amount;
    private List<Sale> sales;
    private int commission;
    private double synd_tax;
    private List<SyndicateTax> synd_service_taxes;

    public PayCheck(int employee_id, LocalDate date, WorkedTime worked_time, String payment_type, double amount,
                    List<Sale> sales, int commission, double synd_tax, List<SyndicateTax> synd_service_taxes) {
        this.employee_id = employee_id;
        this.date = date;
        this.worked_time = worked_time;
        this.payment_type = payment_type;
        this.amount = amount;
        this.sales = sales;
        this.commission = commission;
        this.synd_tax = synd_tax;
        this.synd_service_taxes = synd_service_taxes;
    }

    /*
    public PayCheck(Employee employee){
        this.employee_id = employee.getId();
        this.date = LocalDate.now();
        this.worked_time = employee.getWorked_hours();
        employee.setWorked_hours(new WorkedTime());

        this.commission = 0;
        this.synd_tax = 0;
        this.amount = 0;

        Payment payment = null;
        if(employee instanceof Commissioned) payment = ((Commissioned) employee).getPayment_schedule();
        else if(employee instanceof Salaried) payment = ((Salaried) employee).getPayment_schedule();
        else if(employee instanceof Hourly) payment = ((Hourly) employee).getPayment_schedule();

        Payday payday = null;
        if(payment != null) payday = payment.getPayday();
        if(payday == null) throw new NullPointerException();

        this.payment_type = payment.getType();

        int frequency = 4;
        if(payday instanceof WeeklyPayday){
            frequency = ((WeeklyPayday) payday).getFrequency();
        }

        double frequency_payment = frequency/4;

        if(employee instanceof Commissioned)
        {
            this.commission = ((Commissioned) employee).getCommission_percent();

            while(!((Commissioned) employee).getSales().isEmpty()){
                Sale sale = ((Commissioned) employee).getSales().pop();
                this.amount += sale.getPrice() * (this.commission/100);
                sales.add(sale);
            }

            double monthly_salary = ((Commissioned) employee).getPayment_schedule().getSalary();
            this.amount += monthly_salary * frequency_payment;
        }

        else if(employee instanceof Salaried)
        {
            double salary = ((Salaried) employee).getPayment_schedule().getSalary();
            this.amount += salary;
        }

        else if(employee instanceof Hourly)
        {
            double salary_per_hour = ((Hourly) employee).getPayment_schedule().getSalary();

            int regular_hours = this.worked_time.getRegular();
            int extra_hours = this.worked_time.getExtra();
            this.amount += (salary_per_hour * regular_hours) + 1.5*(salary_per_hour * extra_hours);
        }

        if(employee.getSyndicate().isJoined()){
            Syndicate syndicate = employee.getSyndicate();
            this.synd_tax = syndicate.getTax();
            this.amount -= this.synd_tax;

            while(!syndicate.getSyndicate_taxes().isEmpty()){
                SyndicateTax tax = syndicate.getSyndicate_taxes().pop();
                this.amount -= tax.getValue();
                this.synd_service_taxes.add(tax);
            }
        }
    }
    */
}
