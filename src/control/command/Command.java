/*
 * This Interface and all of its inside packages
 * implements the Command Design Pattern
 */

package control.command;

import control.memento.Caretaker;
import data.Data;

public interface Command {
    void execute(Data data, Caretaker caretaker);
}
