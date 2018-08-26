package models.employees.info;

import models.SyndicateTax;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Syndicate implements Serializable {

    private boolean joined;
    private double tax;
    private List<SyndicateTax> syndical_taxes = new ArrayList<>();

    public Syndicate(boolean joined, double tax){
        this.joined = joined;
        this.tax = tax;
    }
}
