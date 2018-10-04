package control.command.main;

import control.command.Command;
import control.command.main.payday.PaydayMenuHandler;
import control.memento.Caretaker;
import control.utils.GetInfo;
import data.Data;

public class NewPaydayCommand implements Command {
    public void execute(Data data, Caretaker caretaker){
        GetInfo getInfo = new GetInfo();

        int type = getInfo.paydayType();
        PaydayMenuHandler menuHandler = new PaydayMenuHandler();
        menuHandler.handleRequest(type, data, caretaker);
    }
}
