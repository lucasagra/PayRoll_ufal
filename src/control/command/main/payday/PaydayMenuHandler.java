package control.command.main.payday;

import control.command.Command;
import control.command.main.*;
import control.memento.Caretaker;
import data.Data;

import java.util.HashMap;
import java.util.Map;

public class PaydayMenuHandler {
    private Map<Integer, Command> commands = new HashMap<>();

    public PaydayMenuHandler(){
        commands.put(0, new NoCommand());
        commands.put(1, new NewMonthlyPaydayCommand());
        commands.put(2, new NewWeeklyPaydayCommand());
    }

    public void handleRequest(int option, Data data, Caretaker caretaker){
        Command command = commands.get(option);
        command.execute(data, caretaker);
    }
}
