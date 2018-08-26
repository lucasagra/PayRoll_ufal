package data;

import models.employees.Employee;
import models.payday.Payday;
import models.shift.ShiftCard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Data implements Serializable {
    private List<Employee> employees = new ArrayList<>();
    private List<ShiftCard> cards = new ArrayList<>();
    private List<Payday> paydays = new ArrayList<>();

    public void addEmployee(Employee employee){
        if(employee != null) {
            employees.add(employee);
            System.out.println("Employee Successfully added.\n");
        }
    }

    public void editEmployee(Employee toedit, Employee edited){
        if(edited != null) {
            employees.remove(toedit);
            employees.add(edited);
            System.out.println("Employee Successfully edited.\n");
        }
    }

    public void removeEmployee(Employee employee){
        if(employee != null){
            employees.remove(employee);
            System.out.println("Employee Successfully removed.\n");
        }
    }

    public int getEmployeesQuantity() {return employees.size();}

    public List<Employee> getEmployees(){
        return employees;
    }
}
