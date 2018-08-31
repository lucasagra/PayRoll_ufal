package models;

import java.io.Serializable;
import java.time.LocalDate;

public class Sale implements Serializable {
    private LocalDate date;
    private double price;

    public Sale (LocalDate date, double price){
        this.date = date;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString(){
        return "\n   $" + price + " (" + date.toString() + ")";
    }
}
