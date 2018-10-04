package control.command.main;

import control.command.Command;
import control.memento.Caretaker;
import control.memento.Memento;
import control.utils.GetInfo;
import control.utils.Search;
import data.Data;
import models.SyndicateTax;
import models.employees.Employee;

import java.time.LocalDate;

public class InputSyndicateTaxCommand implements Command {
    public void execute(Data data, Caretaker caretaker){
        GetInfo getinfo = new GetInfo();

        int syndicate_id = getinfo.syndId(data);
        if(syndicate_id == 0) return;
        Employee employee = new Search().syndId(data.getEmployees(), syndicate_id);

        SyndicateTax syndicate_tax = new SyndicateTax(LocalDate.now(), getinfo.taxPrice());
        employee.getSyndicate().addSyndicate_taxes(syndicate_tax);
        caretaker.saveState(new Memento(data));
    }
}
