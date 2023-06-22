package orders;

import shoe.Shoe;
import shoe.ShoeManager;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrderManager {
    private List<Orders> orders;
    private String path = "order.csv";

    private static OrderManager orderManager = new OrderManager();

    public static OrderManager getOrderManager(){
        return orderManager;
    }

    ShoeManager shoeWarehouseManager = ShoeManager.getShoeManager();

    private OrderManager() {
        this.orders = new ArrayList<>();
        read();
        setCount();
    }

    public void setCount(){
        int max = 0;
        for(Orders orders1 : orders){
            if (orders1.getId() > max){
                max = orders1.getId();
            }
        }
        Orders.setCount(max);
    }


    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public void add(Orders o) {
        if (o != null) {
            orders.add(o);
            updateQuantity(o);
            save();
        }
    }

    public void updateQuantity(Orders o) {
        HashMap<Integer, Integer> list = o.getShoeBill();
        for (Map.Entry<Integer, Integer> s : list.entrySet()) {
            Shoe shoeWarehouse = shoeWarehouseManager.searchById(s.getKey());
            shoeWarehouse.setQuantity(shoeWarehouse.getQuantity() - s.getValue());
        }
        shoeWarehouseManager.save();
    }

    public void remove(int id) {
        Orders o = searchById(id);
        if (o != null) {
            orders.remove(o);
            save();
        }
    }

    public Orders searchById(int id) {
        read();
        for (Orders orders1 : orders) {
            if (orders1.getId() == id) {
                return orders1;
            }
        }
        return null;
    }

    public List<Orders> searchByCustomer(int id) {
        read();
        List<Orders> orders1 = new ArrayList<>();
        for (Orders o : orders) {
            if (o.getIdCustomer() == id) {
                orders1.add(o);
            }
        }
        return orders1;
    }

    public List<Orders> searchByDate(String date) {
        read();
        List<Orders> orders1 = new ArrayList<>();
        for (Orders o : orders) {
            if (o.getDate().contains(date)) {
                orders1.add(o);
            }
        }
        return orders1;
    }

    public void update(int id, int idCustomer, HashMap<Integer, Integer> hashMap, List<Integer> list, Integer[] integers, String date) {
        Orders orders1 = searchById(id);
        if (orders1 != null) {
            orders1.setIdCustomer(idCustomer);
            orders1.setShoeBill(hashMap);
            orders1.setTotalProduct(list);
            orders1.setTotalBill(integers);
            orders1.setDate(date);
        }
    }

    //1.1: Danh sách các sản phẩm có số lượng bán.
    public HashMap<Integer, Integer> listSale(int month) {
        read();
        HashMap<Integer, Integer> list = new HashMap<>();
        DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        for (Orders orders1 : orders) {
            try {
                if (date.parse(orders1.getDate()).getMonth() + 1 == month) {
                    HashMap<Integer, Integer> shoeBill = orders1.getShoeBill();
                    for (Map.Entry<Integer, Integer> s : shoeBill.entrySet()) {
                        if (!list.containsKey(s.getKey())) {
                            list.put(s.getKey(), s.getValue());
                        } else {
                            list.put(s.getKey(), s.getValue() + list.get(s.getKey()));
                        }
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    // 1: Tìm sản phẩm bán nhiều nhất.
    public String bestSeller(int month) {
        HashMap<Integer, Integer> listMax = listSale(month);
        int max;
        max = Collections.max(listMax.values());
        String out = "";
        for (Map.Entry<Integer, Integer> s : listMax.entrySet()) {
            if (max == s.getValue()) {
                out += "Top selling products with id " + s.getKey() + " and sold for " + max + "\n";
            }
        }
        return out;
    }

    //2: Bán ít nhất.
    public String sellAtLeast(int month) {
        HashMap<Integer, Integer> listMin = listSale(month);
        int min = Collections.min(listMin.values());
        String out = "";
        for (Map.Entry<Integer, Integer> s : listMin.entrySet()) {
            if (min == s.getValue()) {
                out += "Top selling products with id " + s.getKey() + " and sold for " + s.getValue() + "\n";
            }
        }
        return out;
    }


    // Hiển thị id của khách hàng và số tiền khách hàng mua ở cửa hàng.
    public HashMap<Integer, Integer> listCustomer(int month) {
        HashMap<Integer, Integer> list = new HashMap<>();
        DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        for (Orders orders1 : orders) {
            try {
                if (date.parse(orders1.getDate()).getMonth() +1 == month) {
                    Integer[] integers = orders1.getTotalBill();
                    if (!list.containsKey(orders1.getIdCustomer())) {
                        list.put(orders1.getIdCustomer(), integers[1]);
                    } else {
                        list.put(orders1.getIdCustomer(), integers[1] + list.get(orders1.getIdCustomer()));
                    }
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }

    //Hiển thị khách hàng mua nhiều tiền nhất.
    public String customersBuyTheMost(int month) {
        HashMap<Integer, Integer> list = listCustomer(month);
        int max = 0;
        int idCustomer = 0;
        for (Map.Entry<Integer, Integer> s : list.entrySet()) {
            if (max < s.getValue()) {
                max = s.getValue();
                idCustomer = s.getKey();
            }
        }
        return "Customers buy the most with id number is: " + idCustomer + "\n" +
                "Total amount of money is: " + max;
    }

    public void save() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            for (Orders orders1 : orders) {
                bw.write(orders1.toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read() {
        orders.clear();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = "";
            Orders orders1;
            while ((line = br.readLine()) != null) {
                orders1 = handLeLine(line);
                orders.add(orders1);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Orders handLeLine(String line) {
        String[] strings = line.split(",");
        Orders orders1 = new Orders(Integer.parseInt(strings[1]));
        orders1.setId(Integer.parseInt(strings[0]));
        orders1.setDate(strings[2]);
        Integer[] a = new Integer[2];
        a[0] = Integer.parseInt(strings[3]);
        a[1] = Integer.parseInt(strings[4]);
        orders1.setTotalBill(a);
        List<Integer> list = new ArrayList<>();
        int i = 0;
        for (i = 5;i<strings.length-((strings.length-5)/3)*2;i++){
           list.add(Integer.parseInt(strings[i]));
        }
        orders1.setTotalProduct(list);
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int j = i;j<strings.length;j+=2){
            hashMap.put(Integer.parseInt(strings[j]),Integer.parseInt(strings[j+1]));
        }
        orders1.setShoeBill(hashMap);
        return orders1;
    }

    public void display(){
        for (Orders s : orders){
            System.out.println(s.toString());
        }
    }
}
