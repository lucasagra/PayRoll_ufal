package models.employees.info;

import java.io.Serializable;

public class Address implements Serializable {
    private String street;
    private int number;
    private String complement;

    public Address(Address address){
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.complement = address.getComplement();
    }

    public Address (String street, int number, String complement){
        this.street = street;
        this.number = number;
        this.complement = complement;
    }

    public String getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    @Override
    public String toString(){
        return street + ", " + number + ", " + complement + "\n";
    }
}
