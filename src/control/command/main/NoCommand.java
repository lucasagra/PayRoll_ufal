package control.command.main;

import control.command.Command;
import control.memento.Caretaker;
import data.Data;

public class NoCommand implements Command {
    public void execute(Data data, Caretaker caretaker){}
}
