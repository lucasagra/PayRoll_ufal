package control.utils;

import models.employees.info.Address;
import models.employees.info.Syndicate;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GetInfo {

    private Scanner input = new Scanner(System.in);
    private Format format = new Format();

    public Address address(){
        System.out.print("Street name: ");
        String street_name = input.nextLine();
        System.out.print("Number: ");
        int number = format.stringToInt(input.nextLine());
        System.out.print("Complement: ");
        String complement = input.nextLine();

        return new Address(street_name, number, complement);
    }

    public String name(){
        System.out.print("Employee name: ");
        return input.nextLine();
    }

    public Syndicate syndicate(){
        System.out.println("Employee belongs to the syndicate?\n" +
                "[1] - Yes\n" +
                "[0] - No\n");
        boolean joined;
        while(true){
            try {
                joined = format.intToBoolean(format.inputSelect(0, 1));
                break;
            } catch (InputMismatchException e){
                format.invalidInput();
            }
        }

        if(joined) {
            System.out.print("Employee syndicate tax: ");
            int tax = format.stringToInt(input.nextLine());
            return new Syndicate(joined, tax);
        } else {
            return new Syndicate(joined, 0);
        }
    }

    public int employeeType(){
        int option = -1;
        while(option < 0 || option > 3) {
            try {
                System.out.println("Choose employee type:\n" +
                        "[1] - Hourly\n" +
                        "[2] - Salaried\n" +
                        "[3] - Salaried\\Commissioned\n" +
                        "[0] - Cancel");
                option = format.inputSelect(0, 3);
            } catch (InputMismatchException e) {
                format.invalidInput();
            }
        }
        return option;
    }

    public double salary(){
        while(true) {
            try{
                System.out.print("Employee salary: ");
                double salary = format.stringToDouble(input.nextLine());
                return salary;
            } catch (InputMismatchException e){
                format.invalidInput();
            }
        }
    }

    public int commission(){
        while(true) {
            try{
                System.out.print("Employee commission (%): ");
                int commission = format.stringToInt(input.nextLine());
                if(commission < 0 || commission > 100) throw new InputMismatchException();
                return commission;
            } catch (InputMismatchException e) {
                format.invalidInput();
            }
        }
    }
}
