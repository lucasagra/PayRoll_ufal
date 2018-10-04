package control;

import control.utils.*;
import models.employees.*;
import models.employees.info.*;
import models.payday.*;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PaymentControl {

    public Payment getPayment(Employee employee){
        Payment payment = employee.getPayment_schedule();
        if (payment == null) throw new NullPointerException();
        return payment;
    }

    public boolean paydayCheck(Employee employee, LocalDate date){

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

    public Payday choosePayday(Employee employee, List<Payday> paydays){
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
