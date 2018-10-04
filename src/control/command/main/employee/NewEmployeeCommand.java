package control.command.main.employee;

import control.command.Command;
import control.factory.employee.EmployeeFactory;
import control.memento.Caretaker;
import control.memento.Memento;
import control.utils.GetInfo;
import control.utils.Search;
import data.Data;
import models.employees.Employee;

public class NewEmployeeCommand implements Command {
    public void execute(Data data, Caretaker caretaker){
        GetInfo getinfo = new GetInfo();

        EmployeeFactory factory = new Search().getFactory(getinfo.employeeType());

        if(factory != null) {
            Employee employee = factory.createEmployee(data.getEmployeesQuantity() + 1);
            if (employee != null) {
                data.addEmployee(employee);
                caretaker.saveState(new Memento(data));
            }
        }
    }

}
