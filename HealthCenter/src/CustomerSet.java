
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TreeSet;
import java.util.Iterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TanHuynh
 */
public class CustomerSet extends TreeSet<Customer>{
    public void displayAll(){
        for (Customer c : this) {
            System.out.println("--------------------");
            c.output();
            System.out.println("--------------------");
        }
    }
    public void displayAllDesc(){
       Iterator<Customer> it = descendingIterator();
       while(it.hasNext()){
           Customer c = it.next();
           System.out.println("-------------------");
           c.output();
           System.out.println("-------------------");
       }
    }
    public Customer searchById(int findId){
        for (Customer c : this) {
            if(c.getCustId() == findId){
                return c;
            }
        }
        return null;
    }
    
    public Customer displayCustomerBestOder (){
        Customer custBestOder = null;
        for (Iterator<Customer> iterator = this.iterator(); iterator.hasNext();) {
            Customer next = iterator.next();
            if (custBestOder == null || next.getOrderlist().size() > custBestOder.getOrderlist().size()){
                custBestOder = next;
            }
        }
        return custBestOder;
    }
   
    public ArrayList<Customer> getCustOrderByYear(int year) {
        ArrayList<Customer> result = new ArrayList<>();
        for (Customer c : this) {
            for (Order o : c.getOrderlist()) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(o.getOrderDate());
                if (cal.get(Calendar.YEAR) == year) {
                    result.add(c);
                    break;
                }
            }
        }
        return result;
    }
    
    public long[] getRevenueByQuarter(int year) {
        long[] revenueByQuarter = new long[4]; // Array to hold revenue for each quarter

        for (Customer customer : this) {
            for (Order order : customer.getOrderlist()) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(order.getOrderDate());
                int orderYear = cal.get(Calendar.YEAR);
                if (orderYear == year) {
                    int month = cal.get(Calendar.MONTH);
                    int quarter = month / 3; // Determine the quarter (0-based)
                    long totalOrderAmount = order.getTotalMoney();
                    revenueByQuarter[quarter] += totalOrderAmount;
                }
            }
        }

        return revenueByQuarter;
    }
    
    public void displayBestUsedServices(){
        ArrayList<Service> services = new ArrayList<>();
        ArrayList<Integer> counts = new ArrayList<>();

        for (Customer customer : this) {
            for (Order order : customer.getOrderlist()) {
                for (OrderDetail detail : order.getDetaillist()) {
                    Service service = detail.getService();
                    int index = services.indexOf(service);
                    if (index != -1) {
                        counts.set(index, counts.get(index) + 1);
                    } else {
                        services.add(service);
                        counts.add(1);
                    }
                }
            }
        }

        for (int i = 0; i < services.size(); i++) {
            for (int j = i + 1; j < services.size(); j++) {
                if (counts.get(j) > counts.get(i)) {
                    Service tempService = services.get(i);
                    services.set(i, services.get(j));
                    services.set(j, tempService);
                    
                    int tempCount = counts.get(i);
                    counts.set(i, counts.get(j));
                    counts.set(j, tempCount);
                }
            }
        }

        for (int i = 0; i < services.size(); i++) {
            System.out.println("Service: " + services.get(i).getName() + ", Usage: " + counts.get(i));
        }
    }
    
    public void displaySmallistUsedServices(){
        ArrayList<Service> services = new ArrayList<>();
        ArrayList<Integer> counts = new ArrayList<>();

        for (Customer customer : this) {
            for (Order order : customer.getOrderlist()) {
                for (OrderDetail detail : order.getDetaillist()) {
                    Service service = detail.getService();
                    int index = services.indexOf(service);
                    if (index != -1) {
                        counts.set(index, counts.get(index) + 1);
                    } else {
                        services.add(service);
                        counts.add(1);
                    }
                }
            }
        }

        for (int i = 0; i < services.size(); i++) {
            for (int j = i + 1; j < services.size(); j++) {
                if (counts.get(j) < counts.get(i)) {
                    Service tempService = services.get(i);
                    services.set(i, services.get(j));
                    services.set(j, tempService);
                }
            }
        }

        for (int i = 0; i < services.size(); i++) {
            System.out.println("Service: " + services.get(i).getName());
        }
    }
}

