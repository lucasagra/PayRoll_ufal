package control.utils;

import control.Control;
import data.Data;
import models.employees.info.Address;
import models.employees.info.Syndicate;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GetInfo {

    private Scanner input = new Scanner(System.in);
    private Format format = new Format();
    private Search search = new Search();

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

    public Syndicate syndicate(int id){
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
            return new Syndicate(joined, id, tax);
        } else {
            return new Syndicate(joined, 0, 0);
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

    public String paymentType(){
        int option = -1;
        while(option < 1 || option > 3) {
            try {
                System.out.println("Choose payment type:\n" +
                        "[1] - Personally\n" +
                        "[2] - Mail\n" +
                        "[3] - Deposit\n");
                option = format.inputSelect(1, 3);
            } catch (InputMismatchException e) {
                format.invalidInput();
            }
        }
        String type = "";
        switch (option){
            case 1: type = "Personally"; break;
            case 2: type = "Mail"; break;
            case 3: type = "Deposit"; break;
        }

        return type;
    }

    public int id(Data data){
        int id;
        while(true){
            try{
                System.out.print("Input employee id: ");
                id = format.stringToInt(input.nextLine());
                if(id < 0) throw new InputMismatchException();
                else if(id == 0) {
                    format.operationAborted();
                    return id;
                }
                else if(search.id(data.getEmployees(), id) != null) break;
            } catch (InputMismatchException e) {
                format.invalidInput();
            }
        }
        return id;
    }

    public int workedHours(){
        int hours = -1;
        while(hours < 1 || hours > 24){
            try{
                hours = format.stringToInt(input.nextLine());
                if(hours < 1 || hours > 24) throw new InputMismatchException();
            } catch (InputMismatchException e){
                format.invalidInput();
            }
        }

        return hours;
    }

    public double salePrice() {
        System.out.println("Sale price: ");
        double price = -1;
        while(price < 0){
            try {
                price = format.stringToDouble(input.nextLine());
            } catch (InputMismatchException e){
                format.invalidInput();
            }
        }
        return price;
    }

    public double taxPrice() {
        System.out.println("Tax price: ");
        double price = -1;
        while(price < 0){
            try {
                price = format.stringToDouble(input.nextLine());
            } catch (InputMismatchException e){
                format.invalidInput();
            }
        }
        return price;
    }

    public int syndId(Data data){
        int id;
        while(true){
            try{
                System.out.print("Input employee syndicate id: ");
                id = format.stringToInt(input.nextLine());
                if(id < 0) throw new InputMismatchException();
                else if(id == 0) {
                    format.operationAborted();
                    return id;
                }
                else if(search.syndId(data.getEmployees(), id) != null) break;
            } catch (InputMismatchException e) {
                format.invalidInput();
            }
        }
        return id;
    }
}
