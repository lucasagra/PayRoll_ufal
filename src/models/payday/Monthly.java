package models.payday;

import control.utils.Format;

public class Monthly extends Payday{
    private int day;

    public Monthly(String day){
        Format format = new Format();

        if(day.equals("$")) this.day = 32;
        else this.day = format.stringToInt(day);
    }

    public int getDay() {
        return day;
    }

    @Override
    public String toString(){
        String day_str;
        if (day == 32) day_str = "$";
        else day_str = Integer.toString(day);
        return "Monthly " + day_str;
    }
}
