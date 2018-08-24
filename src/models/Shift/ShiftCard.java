package models.Shift;

import models.Date;
import models.Employees.Employee;
import models.Shift.Info.WorkedTime;

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
