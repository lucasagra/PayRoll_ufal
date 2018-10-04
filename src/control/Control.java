package control;

import control.command.main.MainMenuHandler;
import control.memento.Caretaker;
import control.utils.*;
import data.Data;
import views.Menu;

import java.util.InputMismatchException;

public class Control {
    private Menu menu = new Menu();

    private Data data;
    private Caretaker caretaker;

    public Control(Data data){
        this.data = data;
        this.caretaker = new Caretaker(data);
    }

    public void main(){

        int option = -1;
        while(option != 0) {
            try {
                option = menu.main(caretaker);
                if(option == 7){
                    memento();
                } else {
                    MainMenuHandler mainHandler = new MainMenuHandler();
                    mainHandler.handleRequest(option, data, caretaker);
                }

            } catch (InputMismatchException e){
                new Format().invalidInput();
            }
        }
    }

    private void memento(){
        if (caretaker.hasPrevious()) {
            this.data = caretaker.undo().getState();
        }
        else if (caretaker.hasNext()) {
            this.data = caretaker.redo().getState();
        }
    }
}
