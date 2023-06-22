package shoe;

import menu.MenuAll;

import java.util.Scanner;

public class shoeManagerMenu {
    ShoeManager shoeWarehouseManager = ShoeManager.getShoeManager();
    Scanner scanner = new Scanner(System.in);

    public void displayMenu(){
        System.out.println("Menu");
        System.out.println("1: Add Shoe");
        System.out.println("2: Remove Shoe");
        System.out.println("3: Search By Id");
        System.out.println("4: Search By Brand");
        System.out.println("5: Search By Size");
        System.out.println("6: Update");
        System.out.println("7: Shoes larger than 50 pairs");
        System.out.println("8: Shoes are less than 10 pairs");
        System.out.println("9: Show List");
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
                    searchByBrand();
                    break;
                case 5:
                    searchBySize();
                    break;
                case 6:
                    update();
                    break;
                case 7:
                    theMostRemaining();
                    break;
                case 8:
                    lessThan10Pairs();
                    break;
                case 9:
                    display();
                    break;
                default:
                    new MenuAll().menu();
            }
        }
    }

    public void add(){
        System.out.println("Enter id");
        int id = scanner.nextInt();scanner.nextLine();
        id = checkIdShoe(id);
        System.out.println("Enter name");
        String name = scanner.nextLine();
        System.out.println("Enter size");
        int size = scanner.nextInt();scanner.nextLine();
        System.out.println("Enter price");
        int price = scanner.nextInt();scanner.nextLine();
        System.out.println("Enter brand");
        String brand = scanner.nextLine();
        System.out.println("Enter quantity");
        int quantity = scanner.nextInt();scanner.nextLine();
        shoeWarehouseManager.add(new Shoe(id,name,size,price,brand,quantity));
    }

    public int checkIdShoe(int id){
        while (shoeWarehouseManager.searchById(id) != null){
            System.out.println("Re-Enter id");
            id = scanner.nextInt();scanner.nextLine();
        }
        return id;
    }
    public void remove(){
        System.out.println("Enter id");
        int id = scanner.nextInt();scanner.nextLine();
        shoeWarehouseManager.remove(id);
    }
    public void searchById(){
        System.out.println("Enter id");
        int id = scanner.nextInt();scanner.nextLine();
        System.out.println(shoeWarehouseManager.searchById(id));
    }
    public void searchBySize(){
        System.out.println("Enter size");
        int size = scanner.nextInt();scanner.nextLine();
        System.out.println(shoeWarehouseManager.searchBySize(size));
    }
    public void searchByBrand(){
        System.out.println("Enter brand");
        String brand = scanner.nextLine();
        System.out.println(shoeWarehouseManager.searchByBrand(brand));
    }
    public void update(){
        System.out.println("Enter id");
        int id = scanner.nextInt();scanner.nextLine();
        System.out.println("Enter name");
        String name = scanner.nextLine();
        System.out.println("Enter size");
        int size = scanner.nextInt();scanner.nextLine();
        System.out.println("Enter price");
        int price = scanner.nextInt();scanner.nextLine();
        System.out.println("Enter brand");
        String brand = scanner.nextLine();
        System.out.println("Enter quantity");
        int quantity = scanner.nextInt();scanner.nextLine();
        shoeWarehouseManager.update(id,name,size,price,brand,quantity);
    }

    public void theMostRemaining(){
        System.out.println(shoeWarehouseManager.greaterThan50Shoe());
    }

    public void lessThan10Pairs(){
        System.out.println(shoeWarehouseManager.shoeLessThan10());
    }

    public void display(){
        shoeWarehouseManager.display();
    }

}
