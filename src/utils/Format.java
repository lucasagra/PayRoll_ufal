package utils;
import java.util.Scanner;

public class Format {
    private Scanner input = new Scanner(System.in);

    public Format(){}

    public int inputSelect(){
        System.out.print("Select: ");
        return stringToInt(input.nextLine());
    }

    public void invalidInput(){
        System.out.println("Invalid input.");
        input.nextLine();
    }

    public void operationAborted(){
        System.out.println("Operation aborted");
        input.nextLine();
    }

    public int stringToInt(String string){
        try {
            int i = Integer.parseInt(string.trim());
            return i;
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }

}
