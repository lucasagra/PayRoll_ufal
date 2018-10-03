package control.factory;

import control.utils.GetInfo;
import models.employees.Employee;
import models.employees.Salaried;
import models.employees.info.Address;
import models.employees.info.Syndicate;
import models.employees.info.WorkedTime;

public class SalariedFactory implements EmployeeFactory{
    public Employee createEmployee(int id){
        GetInfo getinfo = new GetInfo();

        String name = getinfo.name();
        Address address = getinfo.address();
        Syndicate syndicate = getinfo.syndicate(id);
        String payment_type = getinfo.paymentType();
        double salary = getinfo.salary();

        return new Salaried(id, name, address, syndicate, salary, payment_type, new WorkedTime());
    }

    public Employee editEmployee(Employee employee){
        GetInfo getinfo = new GetInfo();

        if (employee instanceof Salaried) return null;

        double salary = getinfo.salary();

        return new Salaried(employee.getId(), employee.getName(), employee.getAddress(), employee.getSyndicate(),
                salary, employee.getPayment_schedule().getType(), employee.getWorked_hours());
    }
}
