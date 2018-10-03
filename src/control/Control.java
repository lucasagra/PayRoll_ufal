package control;

import control.memento.Caretaker;
import control.memento.Memento;
import control.utils.*;
import data.Data;
import models.PayCheck;
import models.Sale;
import models.SyndicateTax;
import models.employees.Commissioned;
import models.employees.Employee;
import models.ShiftCard;
import models.employees.info.WorkedTime;
import views.Menu;

import java.time.LocalDate;
import java.util.InputMismatchException;

public class Control {

    private GetInfo getinfo = new GetInfo();
    private Search search = new Search();
    private Format format = new Format();
    private Menu menu = new Menu();

    private Data data;
    private Caretaker caretaker;

    public Control(Data data){
        this.data = data;
        this.caretaker = new Caretaker(data);
    }

    public void main(){

        int option = -1;
        while(option != 0) {
            try {
                option = menu.main(caretaker);
                switch (option){
                    case 0: break;
                    case 1: employee(); break;
                    case 2: inputShiftCard(); break;
                    case 3: inputSale(); break;
                    case 4: inputSyndicateTax(); break;
                    case 5: runDailyPayroll(); break;
                    case 6: newPayday(); break;
                    case 7: memento(); break;
                }
            } catch (InputMismatchException e){
                format.invalidInput();
            }
        }
    }

    private void memento(){
        if (caretaker.hasPrevious()) {
            this.data = caretaker.undo().getState();
        }
        else if (caretaker.hasNext()) {
            this.data = caretaker.redo().getState();
        }
    }

    private void employee(){
        EmployeeControl emp_control = new EmployeeControl();

        int option = -1;
        while(option != 0) {
            try {
                option = menu.employee();
                switch (option){
                    case 0: break;
                    case 1: emp_control.newEmployee(data, caretaker); break;
                    case 2: emp_control.removeEmployee(data, caretaker); break;
                    case 3: emp_control.editEmployee(data, caretaker); break;
                    case 4: emp_control.listEmployees(data.getEmployees());  break;
                }
            } catch (InputMismatchException e){
                format.invalidInput();
            }
        }
    }

    private void inputShiftCard() {
        int id = getinfo.id(data);
        if(id == 0) return;
        Employee employee = search.id(data.getEmployees(), id);

        LocalDate date = LocalDate.now();
        int worked_hours = getinfo.workedHours();
        WorkedTime workedtime = new WorkedTime(worked_hours);
        ShiftCard shiftcard = new ShiftCard(id, workedtime, date);

        employee.getWorked_hours().addDailyHours(worked_hours);
        data.addShiftCard(shiftcard);
        caretaker.saveState(new Memento(data));
    }

    private void inputSale() {
        int id = getinfo.id(data);
        if(id == 0) return;
        Employee employee = search.id(data.getEmployees(), id);

        Sale sale = new Sale(LocalDate.now(), getinfo.salePrice());

        if(employee instanceof Commissioned){
            ((Commissioned) employee).addSale(sale);
        }

        data.addSale(sale);
        caretaker.saveState(new Memento(data));
    }

    private void inputSyndicateTax(){
        int syndicate_id = getinfo.syndId(data);
        if(syndicate_id == 0) return;
        Employee employee = search.syndId(data.getEmployees(), syndicate_id);

        SyndicateTax syndicate_tax = new SyndicateTax(LocalDate.now(), getinfo.taxPrice());
        employee.getSyndicate().addSyndicate_taxes(syndicate_tax);
        caretaker.saveState(new Memento(data));
    }

    private void runDailyPayroll(){
        PaymentControl paymentControl = new PaymentControl();
        LocalDate today = LocalDate.now();

        for(Employee employee: data.getEmployees()){
            if(paymentControl.paydayCheck(employee, today)){
                try{
                    PayCheck payCheck = paymentControl.newPayCheck(employee);
                    //money_transfer(payCheck.getAmount(), employee)
                    System.out.println(payCheck.toString());
                    data.addPayCheck(payCheck);
                } catch (NullPointerException e){
                    format.objNotFound();
                }
            }
        }

        caretaker.saveState(new Memento(data));
    }

    private void newPayday(){
        GetInfo getInfo = new GetInfo();
        PaymentControl paymentControl = new PaymentControl();

        int type = getInfo.paydayType();

        switch (type){
            case 0: return;
            case 1: paymentControl.newMonthlyPayday(data); break;
            case 2: paymentControl.newWeeklyPayday(data); break;
        }
    }
}
