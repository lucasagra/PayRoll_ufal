package control.command.main;

import control.command.Command;
import control.memento.Caretaker;
import data.Data;

import java.util.HashMap;
import java.util.Map;

public class MainMenuHandler {

    private Map<Integer, Command> commands = new HashMap<>();

    public MainMenuHandler(){
        commands.put(0, new NoCommand());
        commands.put(1, new EmployeeCommand());
        commands.put(2, new InputShiftCardCommand());
        commands.put(3, new InputSaleCommand());
        commands.put(4, new InputSaleCommand());
        commands.put(5, new InputSyndicateTaxCommand());
        commands.put(6, new RunDailyPayrollCommand());
        commands.put(7, new NewPaydayCommand());
    }

    public void handleRequest(int option, Data data, Caretaker caretaker){
        Command command = commands.get(option);
        command.execute(data, caretaker);
    }

}
