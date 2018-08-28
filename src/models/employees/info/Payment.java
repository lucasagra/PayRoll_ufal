package models.employees.info;
import models.payday.MonthlyPayday;
import models.payday.Payday;
import models.payday.WeeklyPayday;

import java.io.Serializable;

public class Payment implements Serializable {
    private Payday payday;
    private double salary;
    private String type;

    public Payment(Payday payday, double salary, String type){
        this.payday = payday;
        this.salary = salary;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public Payday getPayday() {
        return payday;
    }

    public double getSalary() {
        return salary;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString(){
        double frequency;
        String aux = "";
        if (payday instanceof WeeklyPayday){
            frequency = ((WeeklyPayday) payday).getFrequency();
            aux = " x " + frequency/4 + " (" + frequency + " week(s)) = $" + salary*frequency/4;
        }

        return "Salary: $" + salary + "/month" + aux + "\n" +
                "Payment method: " + type + "\n";
    }
}
