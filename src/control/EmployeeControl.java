package control;

import control.factory.*;
import control.memento.Caretaker;
import control.memento.Memento;
import control.utils.*;
import data.Data;
import models.employees.*;
import models.payday.Payday;
import views.Menu;

import java.util.InputMismatchException;
import java.util.List;

class EmployeeControl {

    private Employee chooseEmployee(List<Employee> employees) {
        Format format = new Format();

        for(int i = 0; i < employees.size(); i++){
            System.out.printf("[%d] - %s - Id: %d%n", i+1, employees.get(i).getName(), employees.get(i).getId());
        }

        while(true) {
            try {
                int option = format.inputSelect(0, employees.size());
                if(option == 0) format.operationAborted();
                else return employees.get(option-1);
                break;
            } catch (InputMismatchException e){
                format.invalidInput();
            }
        }

        return null;
    }

    private EmployeeFactory getFactory(int type){
        if(type == 1) return new HourlyFactory();
        else if(type == 2) return new SalariedFactory();
        else if(type == 3) return new CommissionedFactory();

        new Format().operationAborted();
        return null;
    }

    void newEmployee(Data data, Caretaker caretaker) {
        GetInfo getinfo = new GetInfo();

        EmployeeFactory factory = getFactory(getinfo.employeeType());

        if(factory != null) {
            Employee employee = factory.createEmployee(data.getEmployeesQuantity() + 1);
            if (employee != null) {
                data.addEmployee(employee);
                caretaker.saveState(new Memento(data));
            }
        }
    }

    void removeEmployee(Data data, Caretaker caretaker) {
        System.out.println("Select an employee to remove:");
        Employee employee = chooseEmployee(data.getEmployees());
        if (employee != null){
            data.removeEmployee(employee);
            caretaker.saveState(new Memento(data));
        }
    }

    void editEmployee(Data data, Caretaker caretaker) {
        System.out.println("Select an employee to edit:");
        Employee to_edit = chooseEmployee(data.getEmployees());
        Employee edited = editEmployee(to_edit, data);

        if(edited != null) {
            data.editEmployee(to_edit, edited);
            caretaker.saveState(new Memento(data));
        }
    }

    private Employee editEmployee(Employee employee, Data data){
        Menu menu = new Menu();
        Format format = new Format();
        GetInfo getinfo = new GetInfo();

        while (true) {
            try {
                int option = menu.editEmployee(employee);
                switch (option) {
                    case 0: break;
                    case 1: return editEmployeeType(employee);
                    case 2: employee.setName(getinfo.name()); break;
                    case 3: employee.setAddress(getinfo.address()); break;
                    case 4: employee.setSyndicate(getinfo.syndicate(employee.getId())); break;
                    case 5: editEmployeePayment(employee, data); break;
                    case 6: ((Commissioned) employee).setCommission_percent(getinfo.commission()); break;
                }
                break;
            } catch (InputMismatchException e) {
                format.invalidInput();
            }
        }

        return employee;
    }

    private Employee editEmployeeType(Employee employee){
        GetInfo getinfo = new GetInfo();

        EmployeeFactory factory = getFactory(getinfo.employeeType());
        if (factory == null) return null;

        Employee result = factory.editEmployee(employee);
        if (result != null ) return result;

        return null;
    }

    private void editEmployeePayment(Employee employee, Data data){
        GetInfo getinfo = new GetInfo();
        PaymentControl pay_control = new PaymentControl();

        int option = getinfo.paymentEdit();

        if(option == 1){
            employee.getPayment_schedule().setType(getinfo.paymentType());
        } else if(option == 2){
            Payday newPayday = pay_control.choosePayday(employee, data.getPaydays());
            if(newPayday == null) return;

            employee.getPayment_schedule().setPayday(newPayday);
        }
    }

    void listEmployees(List<Employee> employeeList){
        for(Employee employee: employeeList) {
            System.out.println(employee.toString());
        }
    }
}
