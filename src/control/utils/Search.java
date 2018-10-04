package control.utils;

import control.factory.employee.CommissionedFactory;
import control.factory.employee.EmployeeFactory;
import control.factory.employee.HourlyFactory;
import control.factory.employee.SalariedFactory;
import models.employees.Employee;

import java.util.InputMismatchException;
import java.util.List;

public class Search {

    public Employee id(List<Employee> employee_list, int id){
        for(Employee employee: employee_list){
            if(employee.getId() == id) return employee;
        }
        return null;
    }

    public Employee syndId(List<Employee> employee_list, int id){
        for(Employee employee: employee_list) {
            if (employee.getSyndicate().getSyndicate_id() == id) return employee;
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

    public EmployeeFactory getFactory(int type){
        if(type == 1) return new HourlyFactory();
        else if(type == 2) return new SalariedFactory();
        else if(type == 3) return new CommissionedFactory();

        new Format().operationAborted();
        return null;
    }
}
