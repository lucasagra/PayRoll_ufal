package control.utils;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Format {
    private Scanner input = new Scanner(System.in);

    public Format(){}

    public int inputSelect(int min, int max){
        System.out.print("Select: ");
        int i = stringToInt(input.nextLine());
        if (i < min || i > max) throw new InputMismatchException();
        else return i;
    }

    public void invalidInput(){
        System.out.print("Invalid input.");
        input.nextLine();
    }

    public void operationAborted(){
        System.out.print("Operation aborted.");
        input.nextLine();
    }

    public boolean intToBoolean(int i){
        if (i == 0) return false;
        else if (i == 1) return true;
        else throw new InputMismatchException();
    }

    public int stringToInt(String string){
        try {
            int i = Integer.parseInt(string.trim());
            return i;
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }

    public double stringToDouble(String string){
        try {
            double i = Double.parseDouble(string.trim());
            if (i < 0) throw new InputMismatchException();
            else return i;
        } catch (NumberFormatException nfe) {
            throw new InputMismatchException();
        }
    }

}
