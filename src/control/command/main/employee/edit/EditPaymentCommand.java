package control.command.main.employee.edit;

import control.PaymentControl;
import control.command.main.employee.EditCommand;
import control.factory.employee.EmployeeFactory;
import control.utils.GetInfo;
import data.Data;
import models.employees.Employee;
import models.payday.Payday;

public class EditPaymentCommand implements EditCommand {
    public Employee execute(Employee employee, Data data){
        GetInfo getinfo = new GetInfo();
        PaymentControl pay_control = new PaymentControl();

        int option = getinfo.paymentEdit();

        if(option == 1){
            employee.getPayment_schedule().setType(getinfo.paymentType());
        } else if(option == 2){
            Payday newPayday = pay_control.choosePayday(employee, data.getPaydays());
            if(newPayday != null) {
                employee.getPayment_schedule().setPayday(newPayday);
            }
        }

        return employee;
    }
}
