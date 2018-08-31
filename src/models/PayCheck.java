package models;
import models.employees.Commissioned;
import models.employees.Employee;
import models.employees.Hourly;
import models.employees.Salaried;
import models.employees.info.Payment;
import models.payday.Monthly;
import models.payday.Payday;
import models.payday.Weekly;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class PayCheck implements Serializable {
    private Employee employee;
    private LocalDate date;
    private List<SyndicateTax> synd_service_taxes;
    private List<Sale> sales;
    private double total_sales;
    private double amount;

    public PayCheck(Employee employee, LocalDate date, double amount,
                    List<Sale> sales, List<SyndicateTax> synd_service_taxes, double total_sales) {
        this.employee = employee;
        this.date = date;
        this.sales = sales;
        this.synd_service_taxes = synd_service_taxes;
        this.total_sales = total_sales;
        this.amount = amount;
    }

    private String taxes(){
        if(synd_service_taxes.size() > 0) return "Syndicate service taxes:" +
                synd_service_taxes.stream().map(SyndicateTax::toString).collect(Collectors.joining(",")) + "\n";

        return "";
    }

    private String sales(){
        if(employee instanceof Commissioned) {
            if (sales.size() > 0) {
                double commission = (double) ((Commissioned) employee).getCommission_percent();
                return "Sales made:" + sales.stream().map(Sale::toString).collect(Collectors.joining(",")) + "\n" +
                        "Total Sales: $" + total_sales + " x " + commission + "% = $" + total_sales * commission / 100 + "\n";
            }
        }
        return "";
    }

    private String referenceTime(){
        String referenceTime = "";
        if(employee instanceof Commissioned) {
            Payday payday = ((Commissioned) employee).getPayment_schedule().getPayday();
            if(payday instanceof Weekly)
                referenceTime = ((Weekly)((Commissioned) employee).getPayment_schedule().getPayday()).getFrequency() + " Week(s)\n";
            else if(payday instanceof Monthly)
                referenceTime = "1 Month\n";
        } else if(employee instanceof Hourly){
            referenceTime = ((Weekly)((Hourly) employee).getPayment_schedule().getPayday()).getFrequency() + " Week(s)\n";
        } else if(employee instanceof Salaried) {
            referenceTime = "1 Month\n";
        }

        return referenceTime;
    }
    
    @Override
    public String toString(){
        return  "Date: " + date + "\n" +
                "Time of reference: " + referenceTime() +
                employee.toString() +
                taxes() +
                sales() +
                "Total received: $" + this.amount + "\n";
    }
}
