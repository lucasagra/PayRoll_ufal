package control.utils;

import data.Data;
import models.employees.info.Address;
import models.employees.info.Syndicate;
import models.payday.Payday;

import java.util.InputMismatchException;
import java.util.List;
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
                        "[0] - Cancel\n");
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

    public double hourlyIncome(){
        while(true) {
            try{
                System.out.print("Employee income per hour: ");
                double income = format.stringToDouble(input.nextLine());
                return income;
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
                else if(search.id(data.getEmployees(), id) == null) format.objNotFound();
                else {
                    System.out.println("Employee found.");
                    break;
                }
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
                System.out.print("Hours worked today: ");
                hours = format.stringToInt(input.nextLine());
                if(hours < 1 || hours > 24) throw new InputMismatchException();
            } catch (InputMismatchException e){
                format.invalidInput();
            }
        }

        return hours;
    }

    public double salePrice() {
        System.out.print("Sale price: ");
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
        System.out.print("Tax price: ");
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
                else if(search.syndId(data.getEmployees(), id) == null) format.objNotFound();
                else break;
            } catch (InputMismatchException e) {
                format.invalidInput();
            }
        }
        return id;
    }

    public int paydayType(){
        int option =-1;
        while(option < 0 || option > 2){
            try{
                System.out.println("Select what type of payment model you want to create:\n\n" +
                        "[1] - Monthly\n" +
                        "[2] - Weekly\n" +
                        "[0] - Cancel\n");
                option = format.inputSelect(0,2);
            } catch(InputMismatchException e){
                format.invalidInput();
            }
        }
        return option;
    }

    public int frequency(){
        System.out.println("In how many weeks the payment will be done, periodically?\n");
        int frequency = -1;
        while(frequency < 0){
            try{
                System.out.print("Select: ");
                frequency = format.stringToInt(input.nextLine());
            } catch (InputMismatchException e){
                format.invalidInput();
            }
        }
        if(frequency == 0) format.operationAborted();
        return frequency;
    }

    public int dayOfWeek(){
        int option =-1;
        while(option < 0 || option > 7){
            try{
                System.out.println("Select what day of week the payment will be done:\n\n" +
                        "[1] - Monday\n" +
                        "[2] - Tuesday\n" +
                        "[3] - Wednesday\n" +
                        "[4] - Thursday\n" +
                        "[5] - Friday\n" +
                        "[6] - Saturday\n" +
                        "[7] - Sunday\n" +
                        "[0] - Cancel\n");
                option = format.inputSelect(0,7);
            } catch(InputMismatchException e){
                format.invalidInput();
            }
        }

        if (option == 0) format.operationAborted();
        return option;
    }

    public String dayOfMonth(){
        int option = -1;

        while(option < 0 || option > 3){
            try{
                System.out.println("Select the day for the monthly payment:\n\n" +
                        "[1] - First day of month\n" +
                        "[2] - Select another day (1-28)\n" +
                        "[3] - Last day of month\n" +
                        "[0] - Cancel\n");
                option = format.inputSelect(0, 3);
            } catch (InputMismatchException e){
                format.invalidInput();
            }
        }

        switch (option){
            case 0: format.operationAborted(); return null;
            case 1: return "1";
            case 2: return selectDay();
            case 3: return "$";
        }

        return null;
    }

    private String selectDay(){
        int day = -1;
        while(day <= 0 || day > 28){
            try{
                System.out.print("Select day: ");
                day = format.intInterval(format.stringToInt(input.nextLine()), 1, 28);
            } catch (InputMismatchException e){
                format.invalidInput();
            }
        }

        return String.valueOf(day);
    }

    public int paymentEdit(){
        int option = -1;
        while(option < 0 || option > 2) {
            try {
                System.out.println("What do you want to edit?:\n" +
                        "[1] - Payment type\n" +
                        "[2] - Payment schedule\n" +
                        "[0] - Cancel\n");
                option = format.inputSelect(0, 2);
            } catch (InputMismatchException e) {
                format.invalidInput();
            }
        }
        if(option == 0) format.operationAborted();
        return option;
    }

    public Payday payday(List<Payday> paydays){
        Format format = new Format();

        if(paydays.size() == 0){
            System.out.println("There is no pre defined paydays to choose.");
            return null;
        }

        System.out.println("Select payday from list:");
        for(int i = 0; i < paydays.size(); i++){
            System.out.printf("[%d] - %s%n", i+1, paydays.get(i).toString());
        }
        System.out.println("[0] - Cancel\n");

        int option = -1;
        while(option < 0){
            try{
                System.out.print("Select: ");
                option = format.intInterval(format.stringToInt(input.nextLine()), 0, paydays.size());
            }catch(InputMismatchException e){
                format.invalidInput();
            }
        }

        if(option == 0) return null;
        return paydays.get(option-1);
    }
}












