package control.command.main.employee.edit;

import control.command.main.employee.EditCommand;
import control.factory.employee.EmployeeFactory;
import control.utils.GetInfo;
import data.Data;
import models.employees.Employee;

public class EditAddressCommand implements EditCommand {
    public Employee execute(Employee employee, Data data){
        GetInfo getinfo = new GetInfo();
        employee.setAddress(getinfo.address());
        return employee;
    }
}
