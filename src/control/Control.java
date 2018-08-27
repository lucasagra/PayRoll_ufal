package control;

import control.utils.Format;
import control.utils.GetInfo;
import control.utils.Search;
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

    public void main(Data data){

        int option = -1;
        while(option != 0) {
            try {
                option = menu.main();
                switch (option){
                    case 0: break;
                    case 1: employee(data); break;
                    case 2: inputShiftCard(data); break;
                    case 3: inputSale(data); break;
                    case 4: inputSyndicateTax(data); break;
                    case 5: runDailyPayroll(data); break;
                }
            } catch (InputMismatchException e){
                format.invalidInput();
            }
        }
    }

    private void employee(Data data){
        EmployeeControl emp_control = new EmployeeControl();

        int option = -1;
        while(option != 0) {
            try {
                option = menu.employee();
                switch (option){
                    case 0: break;
                    case 1: data.addEmployee(emp_control.newEmployee(data.getEmployeesQuantity()+1)); break;
                    case 2: data.removeEmployee(emp_control.removeEmployee(data)); break;
                    case 3:
                        Employee to_edit = emp_control.selectEmployeeToEdit(data);
                        Employee edited = emp_control.editEmployee(to_edit);
                        data.editEmployee(to_edit, edited);
                        break;                }
            } catch (InputMismatchException e){
                format.invalidInput();
            }
        }
    }

    private void inputShiftCard(Data data) {
        int id = getinfo.id(data);
        if(id == 0) return;
        Employee employee = search.id(data.getEmployees(), id);

        LocalDate date = LocalDate.now();
        int worked_hours = getinfo.workedHours();
        WorkedTime workedtime = new WorkedTime(worked_hours);
        ShiftCard shiftcard = new ShiftCard(id, workedtime, date);

        employee.getWorked_hours().addDailyHours(worked_hours);
        data.addShiftCard(shiftcard);
    }

    private void inputSale(Data data) {
        int id = getinfo.id(data);
        if(id == 0) return;
        Employee employee = search.id(data.getEmployees(), id);

        Sale sale = new Sale(LocalDate.now(), getinfo.salePrice());
        data.addSale(sale);
        if(employee instanceof Commissioned){
            ((Commissioned) employee).addSale(sale);
        }
    }

    private void inputSyndicateTax(Data data){
        int syndicate_id = getinfo.syndId(data);
        if(syndicate_id == 0) return;
        Employee employee = search.syndId(data.getEmployees(), syndicate_id);

        SyndicateTax syndicate_tax = new SyndicateTax(LocalDate.now(), getinfo.taxPrice());
        employee.getSyndicate().addSyndicate_taxes(syndicate_tax);
    }

    private void runDailyPayroll(Data data){
        PaymentControl paymentControl = new PaymentControl();
        LocalDate today = LocalDate.now();

        for(Employee employee: data.getEmployees()){
            if(paymentControl.paydayCheck(employee, today)){
                try{
                    PayCheck payCheck = paymentControl.newPayCheck(employee);
                    data.addPayCheck(payCheck);
                } catch (NullPointerException e){
                    format.objNotFound();
                }
            }
        }
    }
}
