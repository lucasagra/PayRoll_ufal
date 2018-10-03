package control;

import control.utils.*;
import data.Data;
import models.*;
import models.employees.*;
import models.employees.info.*;
import models.payday.*;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class PaymentControl {

    private Payment getPayment(Employee employee){
        Payment payment = employee.getPayment_schedule();
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
        if(payday instanceof Weekly){
            frequency = ((Weekly) payday).getFrequency();
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

        if(payday instanceof Weekly){
            if(date.getDayOfWeek().getValue() == ((Weekly) payday).getDay()){
                TemporalField temp = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
                return ((date.get(temp) % ((Weekly) payday).getFrequency()) == 0);
            }
        } else if (payday instanceof Monthly){
            if (date.getDayOfMonth() == ((Monthly) payday).getDay()) return true;
            else if (date.getDayOfMonth() == date.lengthOfMonth()){
                return (((Monthly) payday).getDay() == 32);
            }
            else return false;
        }

        return false;
    }

    void newWeeklyPayday(Data data){
        GetInfo getInfo = new GetInfo();

        int frequency = getInfo.frequency();
        if (frequency == 0) return;

        int dayOfWeek = getInfo.dayOfWeek();
        if(dayOfWeek == 0) return;

        data.addPayday(new Weekly(frequency, dayOfWeek));
    }

    void newMonthlyPayday(Data data){
        GetInfo getInfo = new GetInfo();

        String day = getInfo.dayOfMonth();

        if(day == null) return;

        data.addPayday(new Monthly(day));
    }

    Payday choosePayday(Employee employee, List<Payday> paydays){
        GetInfo getInfo = new GetInfo();

        if(employee instanceof Commissioned){
            return getInfo.payday(paydays);
        }
        else if(employee instanceof Salaried){
            List<Payday> monthlyPaydays = new ArrayList<>();
            for(Payday payday: paydays) if(payday instanceof Monthly) monthlyPaydays.add(payday);
            return getInfo.payday(monthlyPaydays);
        }
        else if(employee instanceof Hourly){
            List<Payday> weeklyPaydays = new ArrayList<>();
            for(Payday payday: paydays) if(payday instanceof Weekly) weeklyPaydays.add(payday);
            return getInfo.payday(weeklyPaydays);
        }

        return null;
    }
}
