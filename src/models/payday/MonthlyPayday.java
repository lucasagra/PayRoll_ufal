package models.payday;

import control.utils.Format;
public class MonthlyPayday extends Payday {
    private int day;

    public MonthlyPayday(String day){
        Format format = new Format();

        if(day.equals("$")) this.day = 32;
        else this.day = format.stringToInt(day);
    }

    public int getDay() {
        return day;
    }
}
