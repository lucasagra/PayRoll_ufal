package control.command.main;

import control.command.Command;
import control.memento.Caretaker;
import control.memento.Memento;
import control.utils.GetInfo;
import control.utils.Search;
import data.Data;
import models.Sale;
import models.employees.Commissioned;
import models.employees.Employee;

import java.time.LocalDate;

public class InputSaleCommand implements Command {
    public void execute(Data data, Caretaker caretaker){

        GetInfo getinfo = new GetInfo();

        int id = getinfo.id(data);
        if(id == 0) return;
        Employee employee = new Search().id(data.getEmployees(), id);

        Sale sale = new Sale(LocalDate.now(), getinfo.salePrice());

        if(employee instanceof Commissioned){
            ((Commissioned) employee).addSale(sale);
        }

        data.addSale(sale);
        caretaker.saveState(new Memento(data));
    }
}
