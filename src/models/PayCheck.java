package models;
import models.employees.Commissioned;
import models.employees.Employee;
import models.employees.Hourly;
import models.employees.Salaried;
import models.payday.MonthlyPayday;
import models.payday.WeeklyPayday;

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

    @Override
    public String toString(){
        String taxesString = "";
        String salesString = "";
        String referenceTime = "";

        if(synd_service_taxes.size() > 0) taxesString = "Syndicate service taxes:" +
                synd_service_taxes.stream().map(SyndicateTax::toString).collect(Collectors.joining(",")) + "\n";
        if(employee instanceof Commissioned) {
            referenceTime = ((WeeklyPayday)((Commissioned) employee).getPayment_schedule().getPayday()).getFrequency() + " Week(s)\n";
            if (sales.size() > 0) {
                double commission = (double)((Commissioned) employee).getCommission_percent();
                salesString = "Sales made:" + sales.stream().map(Sale::toString).collect(Collectors.joining(",")) + "\n" +
                        "Total Sales: $" + total_sales + " x " + commission + "% = $" + total_sales * commission / 100 + "\n";
            }
        } else if(employee instanceof Hourly){
            referenceTime = ((WeeklyPayday)((Hourly) employee).getPayment_schedule().getPayday()).getFrequency() + " Week(s)\n";
        } else if(employee instanceof Salaried) {
            referenceTime = " 1 Month\n";
        }

        return  "Date: " + date + "\n" +
                "Time of reference: " + referenceTime +
                employee.toString() +
                taxesString +
                salesString +
                "Total received: $" + this.amount + "\n";
    }
}
