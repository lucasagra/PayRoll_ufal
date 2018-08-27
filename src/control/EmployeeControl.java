package control;

import control.utils.Format;
import control.utils.GetInfo;
import data.Data;
import models.Sale;
import models.employees.*;
import models.employees.info.*;
import models.employees.info.Payment;
import models.payday.Payday;
import models.employees.info.WorkedTime;
import views.Menu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class EmployeeControl {

    public Employee newEmployee(int id) {
        GetInfo getinfo = new GetInfo();
        Format format = new Format();

        String name = getinfo.name();
        Address address = getinfo.address();
        Syndicate syndicate = getinfo.syndicate(id);
        int type = getinfo.employeeType();
        String payment_type = getinfo.paymentType();
        double salary = 0;
        int commission = 0;

        if(type >= 2){
            salary = getinfo.salary();
            if(type == 3) commission = getinfo.commission();
        }

        switch (type){
            case 0: format.operationAborted(); break;
            case 1: return new Hourly(id, name, address, syndicate, payment_type, new WorkedTime());
            case 2: return new Salaried(id, name, address, syndicate, salary, payment_type, new WorkedTime());
            case 3: return new Commissioned(id, name, address, syndicate, salary, commission, payment_type, new WorkedTime());
        }
        return null;
    }

    public Employee chooseEmployee(List<Employee> employees) {
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

    public Employee removeEmployee(Data data) {
        System.out.println("Select an employee to remove:");
        return chooseEmployee(data.getEmployees());
    }

    public Employee selectEmployeeToEdit(Data data) {
        System.out.println("Select an employee to edit:");
        return chooseEmployee(data.getEmployees());
    }

    public Employee editEmployee(Employee employee) {
        if(employee != null) {
            Menu menu = new Menu();
            Format format = new Format();
            GetInfo getinfo = new GetInfo();

            int id = employee.getId();
            String name = employee.getName();
            Address address = employee.getAddress();
            Syndicate syndicate = employee.getSyndicate();
            String payment_type = "";
            Payment payment_schedule = new Payment(new Payday(), 0, "personally");
            List<Sale> sales = new ArrayList<>();
            WorkedTime worked_hours = employee.getWorked_hours();
            int commission_percent = 0;
            int type = 0;

            if (employee instanceof Commissioned) {
                type = 3;
                payment_schedule = ((Commissioned) employee).getPayment_schedule();
                sales = ((Commissioned) employee).getSales();
                commission_percent = ((Commissioned) employee).getCommission_percent();
                payment_type = ((Commissioned) employee).getPayment_schedule().getType();
            } else if (employee instanceof Salaried) {
                type = 2;
                payment_schedule = ((Salaried) employee).getPayment_schedule();
                payment_type = ((Salaried) employee).getPayment_schedule().getType();
            } else if (employee instanceof Hourly) {
                type = 1;
                payment_schedule = ((Hourly) employee).getPayment_schedule();
                payment_type = ((Hourly) employee).getPayment_schedule().getType();
            }

            int new_type = type;
            while (true) {
                try {
                    System.out.println("What do you want to edit");
                    int option = menu.editEmployee(employee);
                    switch (option) {
                        case 0: break;
                        case 1: new_type = getinfo.employeeType(); break;
                        case 2: name = getinfo.name(); break;
                        case 3: address = getinfo.address(); break;
                        case 4: syndicate = getinfo.syndicate(id); break;
                        case 5: payment_type = getinfo.paymentType(); payment_schedule.setType(payment_type); break;
                        case 6: commission_percent = getinfo.commission(); break;
                    }
                    break;
                } catch (InputMismatchException e) {
                    format.invalidInput();
                }
            }
            if(new_type == type) {
                if (type == 1) return new Hourly(id, name, address, syndicate, payment_schedule, worked_hours);
                else if (type == 2) return new Salaried(id, name, address, syndicate, payment_schedule, worked_hours);
                else if (type == 3) return new Commissioned(id, name, address, syndicate, commission_percent, payment_schedule, sales, worked_hours);
            } else if (new_type == 0){
                format.operationAborted();
            } else {
                if(type == 1){
                    double salary = getinfo.salary();
                    if(new_type == 2){
                        return new Salaried(id, name, address, syndicate, salary, payment_type, worked_hours);
                    }
                    else if(new_type == 3) {
                        int commission = getinfo.commission();
                        return new Commissioned(id,name, address, syndicate, salary, commission, payment_type, worked_hours);
                    }
                } else if(type == 2){
                    if(new_type == 1) {
                        return new Hourly(id, name, address, syndicate, payment_type, worked_hours);
                    }
                    else if(new_type == 3) {
                        double salary = getinfo.salary();
                        int commission = getinfo.commission();
                        return new Commissioned(id,name, address, syndicate, salary, commission, payment_type, worked_hours);
                    }
                } else if(type == 3){
                    if(new_type == 1) {
                        return new Hourly(id, name, address, syndicate, payment_type, worked_hours);
                    }
                    else if(new_type == 2) {
                        double salary = getinfo.salary();
                        return new Salaried(id, name, address, syndicate, salary, payment_type, worked_hours);
                    }
                }
            }
        }
        return null;
    }
}
