package models.employees.info;

import java.io.Serializable;

public class Address implements Serializable {
    private String street;
    private int number;
    private String complement;

    public Address (String street, int number, String complement){
        this.street = street;
        this.number = number;
        this.complement = complement;
    }
}
