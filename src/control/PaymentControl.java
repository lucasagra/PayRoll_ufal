package control;

import models.PayCheck;
import models.Sale;
import models.SyndicateTax;
import models.employees.Commissioned;
import models.employees.Employee;
import models.employees.Hourly;
import models.employees.Salaried;
import models.employees.info.Payment;
import models.employees.info.Syndicate;
import models.employees.info.WorkedTime;
import models.payday.MonthlyPayday;
import models.payday.Payday;
import models.payday.WeeklyPayday;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PaymentControl {

    public PayCheck newPayCheck(Employee employee){
        int employee_id = employee.getId();
        LocalDate date = LocalDate.now();
        WorkedTime worked_time = employee.getWorked_hours();
        double amount = 0;
        List<Sale> sales = new ArrayList<>();
        int commission = 0;
        double synd_tax = employee.getSyndicate().getTax();
        List<SyndicateTax> synd_service_taxes = new ArrayList<>();

        Payment payment = null;
        if(employee instanceof Commissioned) payment = ((Commissioned) employee).getPayment_schedule();
        else if(employee instanceof Salaried) payment = ((Salaried) employee).getPayment_schedule();
        else if(employee instanceof Hourly) payment = ((Hourly) employee).getPayment_schedule();

        Payday payday = null;
        if(payment != null) payday = payment.getPayday();
        if(payday == null) throw new NullPointerException();

        String payment_type = payment.getType();

        int frequency = 4;
        if(payday instanceof WeeklyPayday){
            frequency = ((WeeklyPayday) payday).getFrequency();
        }

        double frequency_payment = frequency/4;

        if(employee instanceof Commissioned)
        {
            commission = ((Commissioned) employee).getCommission_percent();

            while(!((Commissioned) employee).getSales().isEmpty()){
                Sale sale = ((Commissioned) employee).getSales().pop();
                amount += sale.getPrice() * (commission/100);
                sales.add(sale);
            }

            double monthly_salary = ((Commissioned) employee).getPayment_schedule().getSalary();
            amount += monthly_salary * frequency_payment;
        }

        else if(employee instanceof Salaried)
        {
            double salary = ((Salaried) employee).getPayment_schedule().getSalary();
            amount += salary;
        }

        else if(employee instanceof Hourly)
        {
            double salary_per_hour = ((Hourly) employee).getPayment_schedule().getSalary();

            int regular_hours = worked_time.getRegular();
            int extra_hours = worked_time.getExtra();
            amount += (salary_per_hour * regular_hours) + 1.5*(salary_per_hour * extra_hours);
        }

        Syndicate syndicate = employee.getSyndicate();
        if(syndicate.isJoined()){
            synd_tax = syndicate.getTax();
            amount -= synd_tax;

            while(!syndicate.getSyndicate_taxes().isEmpty()){
                SyndicateTax tax = syndicate.getSyndicate_taxes().pop();
                amount -= tax.getValue();
                synd_service_taxes.add(tax);
            }
        }

        employee.setWorked_hours(new WorkedTime());
        return new PayCheck(employee_id, date, worked_time, payment_type, amount, sales, commission, synd_tax, synd_service_taxes);
    }

    public boolean paydayCheck(Employee employee, LocalDate date){
        Payment payment = null;

        if(employee instanceof Commissioned) payment = ((Commissioned) employee).getPayment_schedule();
        else if(employee instanceof Salaried) payment = ((Salaried) employee).getPayment_schedule();
        else if(employee instanceof Hourly) payment = ((Hourly) employee).getPayment_schedule();

        if(payment == null) return false;

        Payday payday = payment.getPayday();

        if(payday instanceof WeeklyPayday){
            if(date.getDayOfWeek().getValue() == ((WeeklyPayday) payday).getDay()){
                TemporalField temp = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
                return ((date.get(temp) % ((WeeklyPayday) payday).getFrequency()) == 0);
            }
        } else if (payday instanceof MonthlyPayday){
            if (date.getDayOfMonth() == ((MonthlyPayday) payday).getDay()) return true;
            else if (date.getDayOfMonth() == date.lengthOfMonth()){
                return (((MonthlyPayday) payday).getDay() == 32);
            }
            else return false;
        }

        return false;
    }
}
