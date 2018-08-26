package models;

import java.io.Serializable;
import java.util.Date;

public class SyndicateTax implements Serializable {
    private Date date;
    private double value;

    public SyndicateTax(Date date, double value) {
        this.date = date;
        this.value = value;
    }
}
