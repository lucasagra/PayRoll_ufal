package models;

import java.io.Serializable;
import java.util.Date;

public class Sale implements Serializable {
    private Date date;
    private double price;

    public Sale (Date date, double price){
        this.date = date;
        this.price = price;
    }
}
