package models.payday;

public class WeeklyPayday extends Payday {

    private int frequency;
    private int day;

    public WeeklyPayday(int frequency, int day){
        this.frequency = frequency;
        this.day = day;
    }
}
