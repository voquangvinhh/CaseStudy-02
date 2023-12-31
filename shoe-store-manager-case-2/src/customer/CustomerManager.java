package customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerManager {
    private List<Customer> customers;

    private String path = "customer.csv";

    private static CustomerManager customerManager = new CustomerManager();

    public static CustomerManager getCustomerManager(){
        return customerManager;
    }
    private CustomerManager() {
        this.customers = new ArrayList<>();
        read();
    }

    public void add(Customer customer){
        if (customer!=null){
            customers.add(customer);
            save();
        }
    }

    public void remove(int id){
        Customer c = searchById(id);
        if (c!=null){
            customers.remove(c);
            save();
        }
    }

    public Customer searchById(int id){
        read();
        for (Customer customer : customers){
            if (customer.getId() == id){
                return customer;
            }
        }
        return null;
    }

//    List<Integer>

    public List<Customer> searchByName(String name){
        read();
        List<Customer> customers1 = new ArrayList<>();
        for (Customer customer : customers){
            if (customer.getName().toLowerCase().contains(name.toLowerCase())){
                customers1.add(customer);
            }
        }
        return customers1;
    }

    public List<Customer> searchByAddress(String address){
        read();
        List<Customer> customers1 = new ArrayList<>();
        for (Customer customer : customers){
            if (customer.getAddress().toLowerCase().contains(address.toLowerCase())){
                customers1.add(customer);
            }
        }
        return customers1;
    }

    public void update(int id,String name,int age,String address){
        Customer c = searchById(id);
        if (c!=null){
            c.setName(name);
            c.setAge(age);
            c.setAddress(address);
        }
    }

    public void save(){
        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(path));
            for (Customer customer : customers){
                br.write(customer.toString());
                br.newLine();
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void read(){
        customers.clear();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = "";
            Customer c;
            while ((line = br.readLine())!=null){
                c = handLeLine(line);
                customers.add(c);
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Customer handLeLine(String line){
        String[] strings = line.split(",");
        return new Customer(Integer.parseInt(strings[0]),strings[1],Integer.parseInt(strings[2]),strings[3]);
    }

    public void display(){
        for (Customer customer : customers){
            System.out.println(customer);
        }
    }

}
