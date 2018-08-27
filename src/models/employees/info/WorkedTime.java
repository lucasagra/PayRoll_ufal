package models.employees.info;
import java.io.Serializable;

public class WorkedTime implements Serializable {
    private int regular;
    private int extra;

    public WorkedTime(){
        this.extra = 0;
        this.regular = 0;
    }

    public WorkedTime(int worked_hours){
        this.extra = 0;
        if(worked_hours > 8) {
            this.regular = 8;
            this.extra = worked_hours - 8;
        } else {
            this.regular = worked_hours;
        }
    }

    public void addDailyHours(int hours){
        if(hours > 8) {
            regular += 8;
            extra += hours-8;
        } else {
            regular += hours;
        }
    }
}
