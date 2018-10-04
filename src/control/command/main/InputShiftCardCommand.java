package control.command.main;

import control.command.Command;
import control.memento.Caretaker;
import control.memento.Memento;
import control.utils.GetInfo;
import control.utils.Search;
import data.Data;
import models.ShiftCard;
import models.employees.Employee;
import models.employees.info.WorkedTime;

import java.time.LocalDate;

public class InputShiftCardCommand implements Command {
    public void execute(Data data, Caretaker caretaker){
        GetInfo getinfo = new GetInfo();

        int id = getinfo.id(data);
        if(id == 0) return;
        Employee employee = new Search().id(data.getEmployees(), id);

        LocalDate date = LocalDate.now();
        int worked_hours = getinfo.workedHours();
        WorkedTime workedtime = new WorkedTime(worked_hours);
        ShiftCard shiftcard = new ShiftCard(id, workedtime, date);

        employee.getWorked_hours().addDailyHours(worked_hours);
        data.addShiftCard(shiftcard);
        caretaker.saveState(new Memento(data));
    }
}
