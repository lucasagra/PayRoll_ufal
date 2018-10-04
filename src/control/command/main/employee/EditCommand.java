package control.command.main.employee;

import control.factory.employee.EmployeeFactory;
import data.Data;
import models.employees.Employee;

public interface EditCommand {
    Employee execute(Employee employee, Data data);
}
