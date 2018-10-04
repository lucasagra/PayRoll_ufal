package control.command.main.employee.edit;

import control.command.main.employee.EditCommand;
import control.utils.GetInfo;
import data.Data;
import models.employees.Employee;

public class EditNameCommand implements EditCommand {
    public Employee execute(Employee employee, Data data){
        GetInfo getinfo = new GetInfo();
        employee.setName(getinfo.name());

        return employee;
    }
}
