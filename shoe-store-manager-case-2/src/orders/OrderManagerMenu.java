package orders;

import customer.CustomerManager;
import menu.MenuAll;
import shoe.Shoe;
import shoe.ShoeManager;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class OrderManagerMenu {
    OrderManager orderManager = OrderManager.getOrderManager();

    CustomerManager customerManager = CustomerManager.getCustomerManager();

    ShoeManager shoeWarehouseManager = ShoeManager.getShoeManager();
    Scanner scanner = new Scanner(System.in);
    public void displayMenu(){
        System.out.println("Menu");
        System.out.println("1: Add order");
        System.out.println("2: Remove order");
        System.out.println("3: Search by id");
        System.out.println("4: Search by id customer");
        System.out.println("5: Search by date");
        System.out.println("6: Update order");
        System.out.println("7: Best selling products");
        System.out.println("8: The product that sells the least");
        System.out.println("9: Customers buy the most");
        System.out.println("10: Show bill");
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
                    searchByIdCustomer();
                    break;
                case 5:
                    searchByDate();
                    break;
                case 6:
                    update();
                    break;
                case 7:
                    bestSellingProducts();
                    break;
                case 8:
                    sellAtLeast();
                    break;
                case 9:
                    customersBuyTheMost();
                    break;
                case 10:
                    display();
                    break;
                default:
                    new MenuAll().menu();
            }
        }
    }
    public void add(){
        System.out.println("Enter id customer");
        int idCustomer = scanner.nextInt();scanner.nextLine();
        idCustomer = checkIdCustomer(idCustomer);
        Orders orders = new Orders(idCustomer);
        boolean flag = true;
        while (flag){
            System.out.println("Enter id product");
            int id = scanner.nextInt();scanner.nextLine();
            id = checkIdShoe(id);
            System.out.println("Enter quantity");
            int quantity = scanner.nextInt();scanner.nextLine();
            quantity = checkQuantity(id,quantity);
            orders.addProduct(id,quantity);
            System.out.println(orders.getShoeBill());
            System.out.println("Do you want to print invoices?");
            System.out.println("1:    Yes         2:No");
            int choice = scanner.nextInt();scanner.nextLine();
            switch (choice){
                case 1:
                    flag = false;
                    break;
                case 2:
                    break;
                default:
                    return;
            }
        }
        orders.getTotalProduct();
        orders.getTotalBill();
        orderManager.add(orders);
        System.out.println(orders.getToString());
    }

    public int checkIdCustomer(int id){
        while (customerManager.searchById(id)==null){
            System.out.println("Re-enter id");
            id = scanner.nextInt();scanner.nextLine();
        }
        return id;
    }

    public int checkIdShoe(int id){
        while (shoeWarehouseManager.searchById(id) == null){
            System.out.println("Re-enter id");
            id = scanner.nextInt();scanner.nextLine();
        }
        return id;
    }

    public int checkQuantity(int id,int quantity){
        Shoe shoe = shoeWarehouseManager.searchById(id);
        System.out.println(shoe.getQuantity());
        while (quantity>shoe.getQuantity()||quantity<=0){
            System.out.println("Re-enter quantity");
            quantity = scanner.nextInt();scanner.nextLine();
        }
        return quantity;
    }

    public void remove(){
        System.out.println("Enter id");
        int id = scanner.nextInt();scanner.nextLine();
        orderManager.remove(id);
    }

    public void searchById(){
        System.out.println("Enter id");
        int id = scanner.nextInt();scanner.nextLine();
        System.out.println(orderManager.searchById(id).getToString());
    }

    public void searchByIdCustomer(){
        System.out.println("Enter Customer");
        int id = scanner.nextInt();scanner.nextLine();
        System.out.println(orderManager.searchById(id).getToString());
    }

    public void searchByDate(){
        System.out.println("Enter Date");
        String date = scanner.nextLine();
        System.out.println(orderManager.searchByDate(date));
    }

    public void update(){
        System.out.println("Enter the id to edit");
        int id = scanner.nextInt();scanner.nextLine();
        System.out.println("Enter id customer");
        int idCustomer = scanner.nextInt();scanner.nextLine();
        Orders orders1 = orderManager.searchById(id);
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        Boolean flag = true;
        while (flag){
            System.out.println("Enter the id product edit");
            int idProduct = scanner.nextInt();scanner.nextLine();
            System.out.println("Enter the quantity edit");
            int quantity = scanner.nextInt();scanner.nextLine();
            hashMap.put(idProduct,quantity);
            System.out.println("Do you want to add product edit");
            System.out.println("1: Yes         2:No");
            int choice = scanner.nextInt();scanner.nextLine();
            switch (choice){
                case 1:
                    flag = false;
                    break;
                case 2:
                    break;
                default:
                    return;
            }
        }
        List<Integer> list = orders1.getTotalProduct();
        Integer[] integers = orders1.getTotalBill();
        System.out.println("Enter the date edit");
        String date = scanner.nextLine();
        orderManager.update(id,idCustomer,hashMap,list,integers,date);
    }

    public void bestSellingProducts(){
        System.out.println("Enter month to check");
        int month = scanner.nextInt();scanner.nextLine();
        System.out.println(orderManager.bestSeller(month));
    }

    public void sellAtLeast(){
        System.out.println("Enter month to check");
        int month = scanner.nextInt();scanner.nextLine();
        System.out.println(orderManager.sellAtLeast(month));
    }

    public void customersBuyTheMost(){
        System.out.println("Enter month to check");
        int month = scanner.nextInt();scanner.nextLine();
        System.out.println(orderManager.customersBuyTheMost(month));
    }

    public void display(){
        orderManager.display();
    }

}
