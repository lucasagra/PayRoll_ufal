package control.command.main.payday;

import control.command.Command;
import control.memento.Caretaker;
import control.memento.Memento;
import control.utils.GetInfo;
import data.Data;
import models.payday.Weekly;

public class NewWeeklyPaydayCommand implements Command {
    public void execute(Data data, Caretaker caretaker){
        GetInfo getInfo = new GetInfo();

        int frequency = getInfo.frequency();
        if (frequency == 0) return;

        int dayOfWeek = getInfo.dayOfWeek();
        if(dayOfWeek == 0) return;

        data.addPayday(new Weekly(frequency, dayOfWeek));
        caretaker.saveState(new Memento(data));
    }
}
