package models.employees.info;
import java.io.Serializable;

public class WorkedTime implements Serializable {
    private int regular;
    private int extra;

    public WorkedTime(){
        this.extra = 0;
        this.regular = 0;
    }

    public WorkedTime(WorkedTime workedTime){
        this.regular = workedTime.getRegular();
        this.extra = workedTime.getExtra();
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

    public int getRegular() {
        return regular;
    }

    public int getExtra() {
        return extra;
    }

    @Override
    public String toString(){
       return "Regular hours: " + this.regular + "\n" + "Extra hours: " + this.extra + "\n";
    }
}
