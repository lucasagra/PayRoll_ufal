package models.employees.info;

import models.SyndicateTax;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Syndicate implements Serializable {

    private boolean joined;
    private int syndicate_id;
    private double tax;
    private Stack<SyndicateTax> syndicate_taxes = new Stack<>();

    public Syndicate(boolean joined, int id, double tax){
        this.joined = joined;
        this.syndicate_id = id;
        this.tax = tax;
    }

    public boolean isJoined() {
        return joined;
    }

    public void setJoined(boolean joined) {
        this.joined = joined;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public Stack<SyndicateTax> getSyndicate_taxes() {
        return syndicate_taxes;
    }

    public void addSyndicate_taxes(SyndicateTax syndicate_tax) {
        this.syndicate_taxes.push(syndicate_tax);
        System.out.println("Tax registered to employee.");
    }

    public int getSyndicate_id() {
        return syndicate_id;
    }
}
