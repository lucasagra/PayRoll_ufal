package models.payday;

public class Weekly extends Payday {

    private int frequency;
    private int day;

    public Weekly(int frequency, int day){
        this.frequency = frequency;
        this.day = day;
    }
}
