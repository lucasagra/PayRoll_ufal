package control.command.main.employee;

import control.command.Command;
import control.command.main.NoCommand;
import control.memento.Caretaker;
import data.Data;

import java.util.HashMap;
import java.util.Map;

public class EmployeeMenuHandler {
    private Map<Integer, Command> commands = new HashMap<>();

    public EmployeeMenuHandler(){
        commands.put(0, new NoCommand());
        commands.put(1, new NewEmployeeCommand());
        commands.put(2, new RemoveEmployeeCommand());
        commands.put(3, new EditEmployeeCommand());
        commands.put(4, new ListEmployeesCommand());
    }

    public void handleRequest(int option, Data data, Caretaker caretaker){
        Command command = commands.get(option);
        command.execute(data, caretaker);
    }

}
