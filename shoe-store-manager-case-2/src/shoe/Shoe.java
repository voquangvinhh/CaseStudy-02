package shoe;

public class Shoe {
    private int id;

    private String name;
    private int size;
    private int price;
    private String brand;
    private int quantity;

    public Shoe() {
    }

    public Shoe(int id,String name, int size, int price, String brand, int quantity) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.price = price;
        this.brand = brand;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + size + "," + price + "," + brand + "," + quantity;
    }
}
