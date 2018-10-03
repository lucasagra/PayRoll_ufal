package control.memento;

import data.Data;

public class Caretaker {
    private Memento previous;
    private Memento current;
    private Memento next;

    public Caretaker(Data data){
        current = new Memento(data);
    }

    public void saveState(Memento state){
        this.previous = current;
        this.current = state;
        this.next = null;
    }

    public Memento undo(){
        this.next = current;
        this.current = previous;
        this.previous = null;
        System.out.println("Last action undone.");
        return current;
    }

    public Memento redo(){
        this.previous = current;
        this.current = next;
        this.next = null;
        System.out.println("Last action redone.");
        return current;
    }

    public boolean hasPrevious() {
        return (previous != null);
    }

    public boolean hasNext() {
        return (next != null);
    }
}
