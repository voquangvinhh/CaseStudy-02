package shoe;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ShoeManager {
    private List<Shoe> shoe;

    private String path = "shoe.csv";

    private static ShoeManager shoeManager = new ShoeManager();

    public static ShoeManager getShoeManager(){
        return shoeManager;
    }
    private ShoeManager() {
        this.shoe = new ArrayList<>();
        read();
    }

    public void add(Shoe shoeWarehouse){
        if (shoeWarehouse!=null){
            shoe.add(shoeWarehouse);
            save();
        }
    }

    public void remove(int id){
        if(searchById(id)!=null){
            shoe.remove(searchById(id));
            save();
        }
    }

    public Shoe searchById(int id){
        read();
        for (Shoe shoeWarehouse : shoe){
            if(shoeWarehouse.getId() == id){
                return shoeWarehouse;
            }
        }
        return null;
    }

    public void update(int id,String name,int size,int price,String brand,int quantity){
        Shoe shoeUpdate = searchById(id);
        if (shoeUpdate!=null){
            shoeUpdate.setBrand(brand);
            shoeUpdate.setPrice(price);
            shoeUpdate.setSize(size);
            shoeUpdate.setQuantity(quantity);
            shoeUpdate.setName(name);
        }
        save();
    }

    public List<Shoe> searchByBrand(String brand){
        read();
        List<Shoe> shoeWarehouses = new ArrayList<>();
        for (Shoe shoeWarehouse : shoe){
            if (shoeWarehouse.getBrand().equals(brand)){
                shoeWarehouses.add(shoeWarehouse);
            }
        }
        return shoeWarehouses;
    }

    public List<Shoe> searchBySize(int size){
        read();
        List<Shoe> shoeWarehouses = new ArrayList<>();
        for (Shoe shoeWarehouse : shoe){
            if (shoeWarehouse.getSize() == size){
                shoeWarehouses.add(shoeWarehouse);
            }
        }
        return shoeWarehouses;
    }

    public List<Shoe> greaterThan50Shoe(){
        read();
        List<Shoe> shoeWarehouses = new ArrayList<>();
        for (Shoe shoeWarehouse : shoe){
            if (shoeWarehouse.getQuantity() >= 50 ){
                shoeWarehouses.add(shoeWarehouse);
            }
        }
        return shoeWarehouses;
    }

    public List<Shoe> shoeLessThan10(){
        read();
        List<Shoe> shoeWarehouses = new ArrayList<>();
        for (Shoe shoeWarehouse : shoe){
            if (shoeWarehouse.getQuantity() <= 10 ){
                shoeWarehouses.add(shoeWarehouse);
            }
        }
        return shoeWarehouses;
    }

    public void save(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            for (Shoe shoeWarehouse : shoe){
                bw.write(shoeWarehouse.toString());
                bw.newLine();
            }
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void read(){
        shoe.clear();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = "";
            while ((line = br.readLine())!=null){
                Shoe shoeWarehouse = handLeLine(line);
                shoe.add(shoeWarehouse);
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Shoe handLeLine(String line){
        String[] strings = line.split(",");
        return new Shoe(Integer.parseInt(strings[0]),strings[1],Integer.parseInt(strings[2]),Integer.parseInt(strings[3]),strings[4],Integer.parseInt(strings[5]));
    }

    public void display(){
        for (Shoe shoeWarehouse : shoe){
            System.out.println(shoeWarehouse);
        }
    }

}
