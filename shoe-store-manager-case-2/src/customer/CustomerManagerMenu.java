package customer;

import menu.MenuAll;

import java.io.IOException;
import java.util.Scanner;

public class CustomerManagerMenu {
    CustomerManager customerManager = CustomerManager.getCustomerManager();
    Scanner scanner = new Scanner(System.in);

    public void displayMenu(){
        System.out.println("Menu");
        System.out.println("1: Add customer");
        System.out.println("2: Remove customer");
        System.out.println("3: Search by id");
        System.out.println("4: Search by name");
        System.out.println("5: Search by address");
        System.out.println("6: Update customer");
        System.out.println("7: Show list");
    }

    public void Menu(){
        while (true){
            displayMenu();
            System.out.println("Enter choice");
            int choice = scanner.nextInt();scanner.nextLine();
            switch (choice){
                case 1:
                    add();
                    break;
                case 2:
                    remove();
                    break;
                case 3:
                    searchById();
                    break;
                case 4:
                    searchByName();
                    break;
                case 5:
                    searchByAddress();
                    break;
                case 6:
                    update();
                    break;
                case 7:
                    display();
                    break;
                default:
                    new MenuAll().menu();
            }
        }
    }
    public void add() {
        System.out.println("Enter id");
        int id = 0;
        try {
            id = scanner.nextInt();
            scanner.nextLine();
            id = checkIdCustomer(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Enter name");
        String name = scanner.nextLine();
        System.out.println("Enter age");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter address");
        String address = scanner.nextLine();
        customerManager.add(new Customer(id, name, age, address));
    }

    public int checkIdCustomer(int id){
        while (customerManager.searchById(id) != null){
            System.out.println("Re-Enter id");
            id = scanner.nextInt();scanner.nextLine();
        }
        return id;
    }

    public void remove(){
        System.out.println("Enter id");
        int id = scanner.nextInt();scanner.nextLine();
        customerManager.remove(id);
    }

    public void searchById(){
        System.out.println("Enter id");
        int id = scanner.nextInt();scanner.nextLine();
        System.out.println(customerManager.searchById(id));
    }

    public void searchByName(){
        System.out.println("Enter name");
        String name = scanner.nextLine();
        System.out.println(customerManager.searchByName(name));
    }
    public void searchByAddress(){
        System.out.println("Enter address");
        String address = scanner.nextLine();
        System.out.println(customerManager.searchByAddress(address));
    }

    public void update(){
        System.out.println("Enter id");
        int id = scanner.nextInt();scanner.nextLine();
        System.out.println("Enter name");
        String name = scanner.nextLine();
        System.out.println("Enter age");
        int age = scanner.nextInt();scanner.nextLine();
        System.out.println("Enter address");
        String address = scanner.nextLine();
        customerManager.update(id,name,age,address);
    }

    public void display(){
        customerManager.display();
    }

}
