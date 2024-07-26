
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author TanHuynh
 */
public class Customer implements Comparable<Customer>{

    private long custId;
    private String custname;
    private ArrayList<Pet> petlist;
    private ArrayList<Order> orderlist;

    public Customer() {
        custId = System.currentTimeMillis();
        custname = "Khach Le";
        petlist = new ArrayList<>();
        orderlist = new ArrayList<>();
    }

    public Customer(long custId, String custname, ArrayList<Pet> petlist, ArrayList<Order> orderlist) {
        this.custId = custId;
        this.custname = custname;
        this.petlist = petlist;
        this.orderlist = orderlist;
    }

    public long getCustId() {
        return custId;
    }

    public void setCustId(long custId) {
        this.custId = custId;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public ArrayList<Pet> getPetlist() {
        return petlist;
    }

    public void setPetlist(ArrayList<Pet> petlist) {
        this.petlist = petlist;
    }

    public ArrayList<Order> getOrderlist() {
        return orderlist;
    }

    public void setOrderlist(ArrayList<Order> orderlist) {
        this.orderlist = orderlist;
    }

    public void displayPetlist() {
        for (Pet pet : petlist) {
            System.out.println(pet);
        }
    }

    public boolean addPet(Pet p) {
        return petlist.add(p);
    }

    public void displayOrderlist() {
        for (Order ord : orderlist) {
            ord.output();
        }
    }

    public boolean addOrder(Order o) {
        return orderlist.add(o);
    }

    public void output() {
        System.out.println("custId:" + custId);
        System.out.println("cust name: " + custname);
        displayPetlist();
        displayOrderlist();
    }

    public Pet searchPet(int petId) {
        for (Pet pet : petlist) {
            if (pet.getId() == petId) {
                return pet;
            }
        }
        return null;
    }

    @Override
    public int compareTo(Customer o) {
        if(custId > o.getCustId())  return 1;
        else if(custId < o.getCustId()) return -1;
        return  0;
    }

}
