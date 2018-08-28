package models;

import java.io.Serializable;
import java.time.LocalDate;

public class SyndicateTax implements Serializable {
    private LocalDate date;
    private double value;

    public SyndicateTax(LocalDate date, double value) {
        this.date = date;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString(){
        return " $" + value;
    }
}
