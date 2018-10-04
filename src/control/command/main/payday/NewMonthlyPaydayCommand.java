package control.command.main.payday;

import control.command.Command;
import control.memento.Caretaker;
import control.memento.Memento;
import control.utils.GetInfo;
import data.Data;
import models.payday.Monthly;

public class NewMonthlyPaydayCommand implements Command {
    public void execute(Data data, Caretaker caretaker){
        GetInfo getInfo = new GetInfo();

        String day = getInfo.dayOfMonth();

        if(day == null) return;

        data.addPayday(new Monthly(day));
        caretaker.saveState(new Memento(data));
    }
}
