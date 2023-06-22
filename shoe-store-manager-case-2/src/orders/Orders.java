package orders;

import shoe.Shoe;
import shoe.ShoeManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Orders {

    ShoeManager shoeWarehouseManager = ShoeManager.getShoeManager();
    private int id;
    private int idCustomer;
    private HashMap<Integer, Integer> shoeBill;
    private String date;
    private Integer[] totalBill;

    private List<Integer> totalProduct;

    private static int count = 0;

    private DateFormat date1 = new SimpleDateFormat("dd/MM/yyyy hh:mm");

    public Orders(int idCustomer) {
        this.id = ++count;
        this.idCustomer = idCustomer;
        this.shoeBill = new HashMap<>();
        this.totalProduct = new ArrayList<>();
        this.totalBill = new Integer[2];
        this.date = date1.format(new Date());
    }

    public int getCount() {
        return count;
    }

    public static void setCount(int coun) {
        count = coun;
    }

    public void addProduct(int id, int quantity) {
        if (shoeBill.isEmpty()){
            shoeBill.put(id,quantity);
        }else {
            checkProduct(id,quantity);
        }
    }
    public void checkProduct(int id, int quantity){
        for (Map.Entry<Integer,Integer> s : shoeBill.entrySet()){
            if (!shoeBill.containsKey(s.getKey())){
                shoeBill.put(id,quantity);
            }else shoeBill.put(id,quantity + s.getValue());
        }
    }


    public void setTotalProduct(List<Integer> totalProduct) {
        this.totalProduct = totalProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public HashMap<Integer, Integer> getShoeBill() {
        return shoeBill;
    }

    public void setShoeBill(HashMap<Integer, Integer> shoeBill) {
        this.shoeBill = shoeBill;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public void setTotalBill(Integer[] totalBill) {
        this.totalBill = totalBill;
    }

    public int totalQuantity() {
        int total = 0;
        for (Map.Entry<Integer, Integer> s : shoeBill.entrySet()) {
            total += s.getValue();
        }
        return total;
    }

    //Hiển thị tổng tiền mua của hóa đơn.
    public int totalProduct() {
        int totalMoney = 0;
        for (Integer s : totalProduct) {
            totalMoney += s;
        }
        return totalMoney;
    }

    // Hiển thị số lượng mua và tổng tiền mua.
    public Integer[] getTotalBill() {
        totalBill[0] = totalQuantity();
        totalBill[1] = totalProduct();
        return totalBill;
    }

    // Hiển thị list
    public List<Integer> getTotalProduct() {
        for (Map.Entry<Integer, Integer> s : shoeBill.entrySet()) {
            int total = total(shoeWarehouseManager.searchById(s.getKey()).getPrice(), s.getValue());
            totalProduct.add(total);
        }
        return totalProduct;
    }


    public int total(int price, int quantity) {
        return price * quantity;
    }

    public String getToString() {
        String out = "";
        out += "Id order:     " + id + "\n";
        out += "Id Customer:  " +idCustomer + "\n";
        out += "-------------------------------------" +"\n";
        out += "Name product" + "  " + "Price" + "  " + "Quantity" + " " + "Subtotal" + "\n";
        for (Map.Entry<Integer, Integer> o : shoeBill.entrySet()) {
            Shoe shoeWarehouse = shoeWarehouseManager.searchById(o.getKey());
            out += shoeWarehouse.getName() + "        " + shoeWarehouse.getPrice() + "     " + o.getValue() + "      " + total(shoeWarehouse.getPrice(), o.getValue()) + "\n";
        }
        out += "-------------------------------------" +"\n";
        out += "Total All               " + totalBill[0] + "      " + totalBill[1] + "\n"+ "\n";
        out += "Date                  " + date + "\n";
        return out;
    }

    @Override
    public String toString() {
        String out = "";
        for (Map.Entry<Integer, Integer> s : shoeBill.entrySet()) {
            out +=  "," + s.getKey() + "," + s.getValue();
        }
        String product = "";
        for (Integer integer : totalProduct){
            product += "," + integer;
        }
        return id + "," + idCustomer + "," + date+","+ totalBill[0] + "," + totalBill[1] + product + out;
    }
}
