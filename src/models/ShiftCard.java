package models;
import models.employees.info.WorkedTime;

import java.io.Serializable;
import java.time.LocalDate;

public class ShiftCard implements Serializable {
    private int employee_id;
    private WorkedTime worked_time;
    private LocalDate date;

    public ShiftCard(int employee_id, WorkedTime worked_time, LocalDate date){
        this.employee_id = employee_id;
        this.worked_time = worked_time;
        this.date = date;
    }
}
