package data;

import models.Sale;
import models.employees.Employee;
import models.payday.Payday;
import models.ShiftCard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Data implements Serializable {
    private List<Employee> employees = new ArrayList<>();
    private List<ShiftCard> cards = new ArrayList<>();
    private List<Payday> paydays = new ArrayList<>();
    private List<Sale> sales = new ArrayList<>();

    public void addEmployee(Employee employee){
        if(employee != null) {
            employees.add(employee);
            System.out.println("Employee Successfully added.\n");
        }
    }

    public void editEmployee(Employee to_edit, Employee edited){
        if(edited != null) {
            employees.remove(to_edit);
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

    public void addShiftCard(ShiftCard card){
        if(card != null) {
            cards.add(card);
            System.out.println("ShiftCard successfully registered.\n");
        }
    }

    public void addSale(Sale sale){
        if(sale != null){
            sales.add(sale);
            System.out.println("Sale successfully registered.\n");
        }
    }
}
