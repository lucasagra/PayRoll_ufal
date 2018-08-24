package models.Shift.Info;
import models.Date;

public class WorkedTime {
    private int regular;
    private int extra;

    public WorkedTime(Date date, int worked_hours){
        this.extra = 0;

        if(worked_hours > 8) {
            this.regular = 8;
            this.extra = worked_hours - 8;
        } else {
            this.regular = worked_hours;
        }
    }
}
