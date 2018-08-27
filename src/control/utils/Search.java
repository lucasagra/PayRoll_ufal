package control.utils;

import models.employees.Employee;

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
}
