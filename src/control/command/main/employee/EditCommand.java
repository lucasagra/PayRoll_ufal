package control.command.main.employee;

import data.Data;
import models.employees.Employee;

public interface EditCommand {
    Employee execute(Employee employee, Data data);
}
