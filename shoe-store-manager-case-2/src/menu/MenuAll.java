package menu;

import customer.CustomerManagerMenu;
import orders.OrderManagerMenu;
import shoe.shoeManagerMenu;

import java.util.Scanner;

public class MenuAll {
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Menu");
        System.out.println("1: Menu shoe");
        System.out.println("2: Menu Customer");
        System.out.println("3: Menu Order");
        System.out.println("Enter choice");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                new shoeManagerMenu().Menu();
                break;
            case 2:
                new CustomerManagerMenu().Menu();
                break;
            case 3:
                new OrderManagerMenu().Menu();
                break;
            default:
                return;
        }
    }
}
