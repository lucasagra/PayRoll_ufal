package control.command.main.employee.edit;

import control.command.main.employee.EditCommand;
import control.utils.GetInfo;
import data.Data;
import models.employees.Commissioned;
import models.employees.Employee;

public class EditCommissionCommand implements EditCommand {
    public Employee execute(Employee employee, Data data){
        GetInfo getinfo = new GetInfo();
        ((Commissioned) employee).setCommission_percent(getinfo.commission());

        return employee;
    }
}
