package control;

import control.utils.Clone;
import models.PayCheck;
import models.Sale;
import models.SyndicateTax;
import models.employees.Commissioned;
import models.employees.Employee;
import models.employees.Hourly;
import models.employees.Salaried;
import models.employees.info.Payment;
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

class PaymentControl {

    private Payment getPayment(Employee employee){
        Payment payment = null;
        if(employee instanceof Commissioned) payment = ((Commissioned) employee).getPayment_schedule();
        else if(employee instanceof Salaried) payment = ((Salaried) employee).getPayment_schedule();
        else if(employee instanceof Hourly) payment = ((Hourly) employee).getPayment_schedule();

        if (payment == null) throw new NullPointerException();
        return payment;
    }

    PayCheck newPayCheck(Employee employee){
        Employee employeeCopy = (Employee) Clone.deepCopy(employee);
        if(employeeCopy == null) throw new NullPointerException();

        LocalDate date = LocalDate.now();

        double amount = 0;
        double commission;
        double total_sales = 0;
        List<Sale> sales = new ArrayList<>();
        List<SyndicateTax> synd_service_taxes = new ArrayList<>();
        double synd_tax;

        Payment payment = getPayment(employeeCopy);

        Payday payday = payment.getPayday();
        if(payday == null) throw new NullPointerException();

        int frequency = 4;
        if(payday instanceof WeeklyPayday){
            frequency = ((WeeklyPayday) payday).getFrequency();
        }

        double frequency_payment = (double)frequency/4;

        if(employee instanceof Commissioned)
        {
            commission = (double)((Commissioned) employeeCopy).getCommission_percent();

            while(!((Commissioned) employee).getSales().isEmpty()){
                Sale sale = ((Commissioned) employee).getSales().pop();
                total_sales += sale.getPrice();
                sales.add(sale);
            }

            amount += (total_sales * (commission/100));

            double monthly_salary = ((Commissioned) employeeCopy).getPayment_schedule().getSalary();
            amount += (monthly_salary * frequency_payment);
        }
        else if(employee instanceof Salaried)
        {
            double salary = ((Salaried) employeeCopy).getPayment_schedule().getSalary();
            amount += salary;
        }
        else if(employee instanceof Hourly)
        {
            double salary_per_hour = ((Hourly) employeeCopy).getPayment_schedule().getSalary();

            int regular_hours = employeeCopy.getWorked_hours().getRegular();
            int extra_hours = employeeCopy.getWorked_hours().getExtra();
            amount += ((salary_per_hour * regular_hours) + 1.5*(salary_per_hour * extra_hours));
        }

        if(employeeCopy.getSyndicate().isJoined()){
            synd_tax = employeeCopy.getSyndicate().getTax() * frequency_payment;
            amount -= synd_tax;
            while(!employee.getSyndicate().getSyndicate_taxes().isEmpty()){
                SyndicateTax tax = employee.getSyndicate().getSyndicate_taxes().pop();
                amount -= tax.getValue();
                synd_service_taxes.add(tax);
            }
        }

        employee.setWorked_hours(new WorkedTime());
        return new PayCheck(employeeCopy, date, amount, sales, synd_service_taxes, total_sales);
    }

    boolean paydayCheck(Employee employee, LocalDate date){

        Payment payment = getPayment(employee);

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
