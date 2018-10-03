package control.factory;

import control.utils.GetInfo;
import models.employees.Employee;
import models.employees.Hourly;
import models.employees.info.Address;
import models.employees.info.Syndicate;
import models.employees.info.WorkedTime;

public class HourlyFactory implements EmployeeFactory{
    public Employee createEmployee(int id){
        GetInfo getinfo = new GetInfo();

        String name = getinfo.name();
        Address address = getinfo.address();
        Syndicate syndicate = getinfo.syndicate(id);
        String payment_type = getinfo.paymentType();
        double income = getinfo.hourlyIncome();

        return new Hourly(id, name, address, syndicate, income, payment_type, new WorkedTime());
    }
    public Employee editEmployee(Employee employee) {
        GetInfo getinfo = new GetInfo();

        if (employee instanceof Hourly) return null;

        double income = getinfo.hourlyIncome();
        return new Hourly(employee.getId(), employee.getName(), employee.getAddress(), employee.getSyndicate(),
                income, employee.getPayment_schedule().getType(), employee.getWorked_hours());
    }
}
