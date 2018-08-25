package models.shift;

import models.employees.Employee;
import models.shift.info.WorkedTime;

import java.util.Date;

public class ShiftCard {
    private Employee employee;
    private WorkedTime worked_time;
    private Date date;

    public ShiftCard(Employee employee, WorkedTime worked_time, Date date){
        this.employee = employee;
        this.worked_time = worked_time;
        this.date = date;
    }
}
