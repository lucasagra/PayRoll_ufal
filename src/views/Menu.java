package views;

import control.utils.Format;
import models.employees.Commissioned;
import models.employees.Employee;
import models.employees.Hourly;
import models.employees.Salaried;


public class Menu {

    private Format format = new Format();

    public int main(){
        System.out.println("Payroll System \n\n" +
                "[1] - Manage employees\n" +
                "[2] - Input ShiftCard\n" +
                "[3] - Input Sale\n" +
                "[4] - Input Syndicate Service Tax\n" +
                "[5] - Run Daily Payroll\n" +
                "[6] - New payday preset to calendar\n" +
                "[0] - Exit\n");

        return format.inputSelect(0, 6);
    }

    public int employee(){
        System.out.println("Employee manager: \n\n" +
                "[1] - Add employee\n" +
                "[2] - Remove employee\n" +
                "[3] - Edit employee\n" +
                "[4] - Show employees\n" +
                "[0] - Back\n");

        return format.inputSelect(0, 4);
    }

    public int editEmployee(Employee employee){
        if(employee instanceof Commissioned) {
            System.out.println("What do you want to edit: \n\n" +
                    "[1] - Type\n" +
                    "[2] - Name\n" +
                    "[3] - Address\n" +
                    "[4] - Syndicate\n" +
                    "[5] - Payment method\n" +
                    "[6] - Commission Percent\n" +
                    "[0] - Cancel");
            return format.inputSelect(0, 6);
        } else if (employee instanceof Salaried) {
            System.out.println("What do you want to edit: \n\n" +
                    "[1] - Type\n" +
                    "[2] - Name\n" +
                    "[3] - Address\n" +
                    "[4] - Syndicate\n" +
                    "[5] - Payment method\n" +
                    "[0] - Cancel\n");
            return format.inputSelect(0, 5);
        } else if(employee instanceof Hourly){
            System.out.println("What do you want to edit: \n\n" +
                    "[1] - Type\n" +
                    "[2] - Name\n" +
                    "[3] - Address\n" +
                    "[4] - Syndicate\n" +
                    "[5] - Payment method\n" +
                    "[0] - Cancel\n");
            return format.inputSelect(0, 5);
        } else return 0;
    }
}
