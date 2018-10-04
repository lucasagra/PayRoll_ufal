package control.command.main;

import control.command.Command;
import control.command.main.employee.EmployeeMenuHandler;
import control.memento.Caretaker;
import control.utils.Format;
import data.Data;
import views.Menu;

import java.util.InputMismatchException;

public class EmployeeCommand implements Command {
    public void execute(Data data, Caretaker caretaker){
        int option = -1;
        while(option != 0) {
            try {
                option = new Menu().employee();
                EmployeeMenuHandler menuHandler = new EmployeeMenuHandler();
                menuHandler.handleRequest(option, data, caretaker);
            } catch (InputMismatchException e){
                new Format().invalidInput();
            }
        }
    }
}
