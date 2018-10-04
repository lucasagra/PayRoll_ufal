package control.factory;

import control.PaymentControl;
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
import models.payday.Payday;
import models.payday.Weekly;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PayCheckFactory {

    public PayCheck newPayCheck(Employee employee){
        Employee employeeCopy = (Employee) Clone.deepCopy(employee);
        if(employeeCopy == null) throw new NullPointerException();

        LocalDate date = LocalDate.now();

        double amount = 0;
        double commission;
        double total_sales = 0;
        List<Sale> sales = new ArrayList<>();
        List<SyndicateTax> synd_service_taxes = new ArrayList<>();
        double synd_tax;

        Payment payment = new PaymentControl().getPayment(employeeCopy);

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

            double monthly_salary = employeeCopy.getPayment_schedule().getSalary();
            amount += (monthly_salary * frequency_payment);
        }
        else if(employee instanceof Salaried)
        {
            double salary = employeeCopy.getPayment_schedule().getSalary();
            amount += salary;
        }
        else if(employee instanceof Hourly)
        {
            double salary_per_hour = employeeCopy.getPayment_schedule().getSalary();

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
}
