package control.command.main.employee;

import control.command.Command;
import control.memento.Caretaker;
import control.memento.Memento;
import control.utils.Search;
import data.Data;
import models.employees.Employee;

public class RemoveEmployeeCommand implements Command {

    public void execute(Data data, Caretaker caretaker){
        System.out.println("Select an employee to remove:");
        Employee employee = new Search().chooseEmployee(data.getEmployees());
        if (employee != null){
            data.removeEmployee(employee);
            caretaker.saveState(new Memento(data));
        }
    }
}
