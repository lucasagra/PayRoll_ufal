package models;

import java.util.Date;

public class SyndicalTax {
    private Date date;
    private double value;

    public SyndicalTax(Date date, double value) {
        this.date = date;
        this.value = value;
    }
}
