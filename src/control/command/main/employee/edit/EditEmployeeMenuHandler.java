package control.command.main.employee.edit;

import control.command.main.employee.EditCommand;
import control.command.main.employee.EditNoCommand;
import data.Data;
import models.employees.Employee;
import java.util.HashMap;
import java.util.Map;

public class EditEmployeeMenuHandler {
    private Map<Integer, EditCommand> commands = new HashMap<>();

    private Employee employee;

    public EditEmployeeMenuHandler(Employee employee){
        this.employee = employee;

        commands.put(0, new EditNoCommand());
        commands.put(1, new EditTypeCommand());
        commands.put(2, new EditNameCommand());
        commands.put(3, new EditAddressCommand());
        commands.put(4, new EditSyndicateCommand());
        commands.put(5, new EditPaymentCommand());
        commands.put(6, new EditCommissionCommand());
    }

    public Employee handleRequest(int option, Data data){
        EditCommand command = commands.get(option);
        return command.execute(employee, data);
    }
}
