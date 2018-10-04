package control.command.main.employee;

import control.command.Command;
import control.command.main.employee.edit.EditEmployeeMenuHandler;
import control.memento.Caretaker;
import control.memento.Memento;
import control.utils.Format;
import control.utils.Search;
import data.Data;
import models.employees.Employee;
import views.Menu;

import java.util.InputMismatchException;

public class EditEmployeeCommand implements Command {

    public void execute(Data data, Caretaker caretaker){
        System.out.println("Select an employee to edit:");
        Employee to_edit = new Search().chooseEmployee(data.getEmployees());
        Employee edited = editEmployee(to_edit, data);

        if(edited != null) {
            data.editEmployee(to_edit, edited);
            caretaker.saveState(new Memento(data));
        }
    }

    private Employee editEmployee(Employee employee, Data data){
        Menu menu = new Menu();
        Format format = new Format();

        while (true) {
            try {
                int option = menu.editEmployee(employee);
                EditEmployeeMenuHandler menuHandler = new EditEmployeeMenuHandler(employee);
                employee = menuHandler.handleRequest(option, data);
                break;
            } catch (InputMismatchException e) {
                format.invalidInput();
            }
        }

        return employee;
    }

}
