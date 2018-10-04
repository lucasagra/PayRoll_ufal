package control.command;

import control.memento.Caretaker;
import data.Data;

public interface Command {
    void execute(Data data, Caretaker caretaker);
}
