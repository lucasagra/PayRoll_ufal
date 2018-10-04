package control.command.main.employee.edit;

import control.command.main.employee.EditCommand;
import control.factory.employee.EmployeeFactory;
import control.utils.GetInfo;
import control.utils.Search;
import data.Data;
import models.employees.Employee;

public class EditTypeCommand implements EditCommand {
    public Employee execute(Employee employee, Data data){
        GetInfo getinfo = new GetInfo();

        EmployeeFactory factory = new Search().getFactory(getinfo.employeeType());

        if (factory == null) return null;

        Employee result = factory.editEmployee(employee);
        if (result != null) {
            return result;
        }

        return null;
    }
}
