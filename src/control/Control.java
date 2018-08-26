package control;

import control.utils.Format;
import data.Data;
import models.employees.Employee;
import views.Menu;

import java.util.InputMismatchException;

public class Control {

    public void main(Data data){
        Menu menu = new Menu();
        Format format = new Format();

        int option = -1;
        while(option != 0) {
            try {
                option = menu.main();
                switch (option){
                    case 0: break;
                    case 1: employee(data); break; // manage employee
                    case 2: break; // input shiftcard
                    case 3: break; // input sale
                    case 4: break; // input syndicate service tax
                    case 5: break; // run daily payroll
                }
            } catch (InputMismatchException e){
                format.invalidInput();
            }
        }
    }

    private void employee(Data data){
        Menu menu = new Menu();
        Format format = new Format();
        EmployeeControl emp_control = new EmployeeControl();

        int option = -1;
        while(option != 0) {
            try {
                option = menu.employee();
                switch (option){
                    case 0: break;
                    case 1: data.addEmployee(emp_control.newEmployee(data.getEmployeesQuantity())); break;
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
}
