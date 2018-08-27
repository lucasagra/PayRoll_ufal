package models.employees.info;
import models.payday.Payday;
import java.io.Serializable;

public class Payment implements Serializable {
    private Payday payday;
    private double salary;
    private String type;

    public Payment(Payday payday, double salary, String type){
        this.payday = payday;
        this.salary = salary;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
