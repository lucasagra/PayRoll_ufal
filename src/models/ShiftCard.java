package models;
import models.employees.info.WorkedTime;

import java.io.Serializable;
import java.util.Date;

public class ShiftCard implements Serializable {
    private int employee_id;
    private WorkedTime worked_time;
    private Date date;

    public ShiftCard(int employee_id, WorkedTime worked_time, Date date){
        this.employee_id = employee_id;
        this.worked_time = worked_time;
        this.date = date;
    }
}
