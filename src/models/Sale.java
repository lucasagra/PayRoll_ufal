package models;

import java.util.Date;

public class Sale {
    private Date date;
    private double price;

    public Sale (Date date, double price){
        this.date = date;
        this.price = price;
    }
}
