
import java.io.Serializable;
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author TanHuynh
 */
public class Service implements Comparable<Service>, Serializable {

    private int id;
    private String name;
    private double price;

    public Service() {
    }

    public Service(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Service{" + "id=" + id + ", name=" + name + ", price=" + price + '}';
    }

    @Override
    public int compareTo(Service o) {
        if (price > o.getPrice()) {
            return 1;
        } else if (price < o.getPrice()) {
            return -1;
        }
        return 0;

    }
    public static Comparator<Service>tmp= new Comparator<Service>() {
        @Override
        public int compare(Service o1, Service o2) {
            return o1.getName().compareTo(o2.getName());
            
        }
    };

}
