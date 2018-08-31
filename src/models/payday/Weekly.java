package models.payday;

import java.time.DayOfWeek;

public class Weekly extends Payday {

    private int frequency;
    private int day;

    public Weekly(int frequency, int day){
        this.frequency = frequency;
        this.day = day;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getDay() {
        return day;
    }

    @Override
    public String toString(){
        return "Weekly " + frequency + " " + DayOfWeek.of(day);
    }
}
