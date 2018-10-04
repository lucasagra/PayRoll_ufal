package control.command.main.employee;

import control.command.Command;
import control.memento.Caretaker;
import data.Data;
import models.employees.Employee;

import java.util.List;

public class ListEmployeesCommand implements Command {
    public void execute(Data data, Caretaker caretaker){
        List<Employee> employeeList = data.getEmployees();

        for(Employee employee: employeeList) {
            System.out.println(employee.toString());
        }
    }
}
