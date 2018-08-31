package control;

import control.utils.Format;
import control.utils.GetInfo;
import data.Data;
import models.employees.*;
import models.employees.info.*;
import models.employees.info.WorkedTime;
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

    void newEmployee(Data data) {
        GetInfo getinfo = new GetInfo();
        Format format = new Format();

        int id = data.getEmployeesQuantity()+1;
        String name = getinfo.name();
        Address address = getinfo.address();
        Syndicate syndicate = getinfo.syndicate(id);
        int type = getinfo.employeeType();
        String payment_type = getinfo.paymentType();
        double salary = 0;
        double income = 0;
        int commission = 0;

        if(type == 1) {
            income = getinfo.hourlyIncome();
        } else if(type >= 2){
            salary = getinfo.salary();
            if(type == 3) commission = getinfo.commission();
        }

        switch (type){
            case 0: format.operationAborted(); break;
            case 1: data.addEmployee(new Hourly(id, name, address, syndicate, income, payment_type, new WorkedTime())); break;
            case 2: data.addEmployee(new Salaried(id, name, address, syndicate, salary, payment_type, new WorkedTime())); break;
            case 3: data.addEmployee(new Commissioned(id, name, address, syndicate, salary, commission, payment_type, new WorkedTime())); break;
        }
    }

    void removeEmployee(Data data) {
        System.out.println("Select an employee to remove:");
        data.removeEmployee(chooseEmployee(data.getEmployees()));
    }

    void editEmployee(Data data) {
        System.out.println("Select an employee to edit:");
        Employee to_edit = chooseEmployee(data.getEmployees());
        Employee edited = editEmployee(to_edit, data);

        if(edited != null) data.editEmployee(to_edit, edited);
    }

    private Employee editEmployeeType(Employee employee){
        GetInfo getinfo = new GetInfo();

        int new_type = getinfo.employeeType();
        if (new_type == 0) return null;
        double salary;
        double income;
        int commission;

        if(employee instanceof Commissioned) {
            if (new_type == 1) {
                income = getinfo.hourlyIncome();
                return new Hourly(employee.getId(), employee.getName(), employee.getAddress(), employee.getSyndicate(),
                        income, ((Commissioned) employee).getPayment_schedule().getType(), employee.getWorked_hours());
            } else if (new_type == 2) {
                salary = getinfo.salary();
                return new Salaried(employee.getId(), employee.getName(), employee.getAddress(), employee.getSyndicate(),
                        salary, ((Commissioned) employee).getPayment_schedule().getType(),employee.getWorked_hours());
            }
        } else if(employee instanceof Salaried) {
            if (new_type == 1) {
                income = getinfo.hourlyIncome();
                return new Hourly(employee.getId(), employee.getName(), employee.getAddress(), employee.getSyndicate(),
                        income, ((Salaried) employee).getPayment_schedule().getType(), employee.getWorked_hours());
            } else if (new_type == 3) {
                salary = getinfo.salary();
                commission = getinfo.commission();
                return new Commissioned(employee.getId(), employee.getName(), employee.getAddress(), employee.getSyndicate(),
                        salary, commission, ((Salaried) employee).getPayment_schedule().getType(), employee.getWorked_hours());
            }
        } else if(employee instanceof Hourly){
            salary = getinfo.salary();
            if(new_type == 2) {
                return new Salaried(employee.getId(), employee.getName(), employee.getAddress(), employee.getSyndicate(),
                        salary, ((Hourly) employee).getPayment_schedule().getType(), employee.getWorked_hours());
            }
            else if(new_type == 3) {
                commission = getinfo.commission();
                return new Commissioned(employee.getId(), employee.getName(), employee.getAddress(), employee.getSyndicate(),
                        salary, commission, ((Hourly) employee).getPayment_schedule().getType(), employee.getWorked_hours());
            }
        }

        return null;
    }

    private void editEmployeePayment(Employee employee, Data data){
        GetInfo getinfo = new GetInfo();
        PaymentControl pay_control = new PaymentControl();

        int option = getinfo.paymentEdit();

        if(option == 1){
            if(employee instanceof  Commissioned) ((Commissioned) employee).getPayment_schedule().setType(getinfo.paymentType());
            else if(employee instanceof Salaried) ((Salaried) employee).getPayment_schedule().setType(getinfo.paymentType());
            else if(employee instanceof  Hourly) ((Hourly) employee).getPayment_schedule().setType(getinfo.paymentType());

        } else if(option == 2){
            Payday newPayday = pay_control.choosePayday(employee, data.getPaydays());
            if(newPayday == null) return;

            if(employee instanceof  Commissioned)
                ((Commissioned) employee).getPayment_schedule().setPayday(newPayday);
            else if(employee instanceof Salaried)
                ((Salaried) employee).getPayment_schedule().setPayday(newPayday);
            else if(employee instanceof  Hourly)
                ((Hourly) employee).getPayment_schedule().setPayday(newPayday);
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
                    case 6: if(employee instanceof Commissioned) ((Commissioned) employee).setCommission_percent(getinfo.commission()); break;
                }
                break;
            } catch (InputMismatchException e) {
                format.invalidInput();
            }
        }

        return null;
    }

    void listEmployees(List<Employee> employeeList){
        for(Employee employee: employeeList) {
            System.out.println(employee.toString());
        }
    }
}
