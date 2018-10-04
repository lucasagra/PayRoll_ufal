package control.factory.employee;

import control.utils.GetInfo;
import models.employees.Commissioned;
import models.employees.Employee;
import models.employees.info.*;

public class CommissionedFactory implements EmployeeFactory{
    public Employee createEmployee(int id){
        GetInfo getinfo = new GetInfo();

        String name = getinfo.name();
        Address address = getinfo.address();
        Syndicate syndicate = getinfo.syndicate(id);
        String payment_type = getinfo.paymentType();
        double salary = getinfo.salary();
        int commission = getinfo.commission();;

        return new Commissioned(id, name, address, syndicate, salary, commission, payment_type, new WorkedTime());
    }
    public Employee editEmployee(Employee employee){
        GetInfo getinfo = new GetInfo();
        if (employee instanceof Commissioned) return null;

        double salary = getinfo.salary();
        int commission = getinfo.commission();

        return new Commissioned(employee.getId(), employee.getName(), employee.getAddress(), employee.getSyndicate(),
                salary, commission, employee.getPayment_schedule().getType(), employee.getWorked_hours());
    }
}
