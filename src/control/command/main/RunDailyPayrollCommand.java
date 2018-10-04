package control.command.main;

import control.PaymentControl;
import control.command.Command;
import control.factory.PayCheckFactory;
import control.memento.Caretaker;
import control.memento.Memento;
import control.utils.Format;
import data.Data;
import models.PayCheck;
import models.employees.Employee;

import java.time.LocalDate;

public class RunDailyPayrollCommand implements Command {
    public void execute(Data data, Caretaker caretaker){
        PaymentControl paymentControl = new PaymentControl();
        LocalDate today = LocalDate.now();

        for(Employee employee: data.getEmployees()){
            if(paymentControl.paydayCheck(employee, today)){
                try{
                    PayCheck payCheck = new PayCheckFactory().newPayCheck(employee);
                    //money_transfer(payCheck.getAmount(), employee)
                    System.out.println(payCheck.toString());
                    data.addPayCheck(payCheck);
                } catch (NullPointerException e){
                    new Format().objNotFound();
                }
            }
        }

        caretaker.saveState(new Memento(data));
    }
}
