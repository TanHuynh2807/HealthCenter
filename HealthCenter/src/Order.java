
import java.util.ArrayList;
import java.util.Date;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author TanHuynh
 */
public class Order {

    private long id;
    private Date orderDate;
    private ArrayList<OrderDetail> detaillist;

    public Order() {
        id = System.currentTimeMillis();
        orderDate = new Date();
        detaillist = new ArrayList<>();
    }

    public Order(long id, Date orderDate, ArrayList<OrderDetail> detaillist) {
        this.id = id;
        this.orderDate = orderDate;
        this.detaillist = detaillist;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public ArrayList<OrderDetail> getDetaillist() {
        return detaillist;
    }

    public void setDetaillist(ArrayList<OrderDetail> detaillist) {
        this.detaillist = detaillist;
    }

    public void output() {
        System.out.println("order id " + id);
        System.out.println("order date" + orderDate);
        System.out.println("DS chi tiet dat hang: ");
        for (OrderDetail orderDetail : detaillist) {
            System.out.println(orderDetail);
        }
        System.out.println("tong tien: "+getTotalMoney()+"vnd");
    }

    public boolean addDetail(OrderDetail o) {
        return detaillist.add(o);
    }
    
    public long getTotalMoney(){
            long total =0;
            for (OrderDetail orderDetail : detaillist) {
            total = (long) (total + orderDetail.getService().getPrice());
        }
            return total;
    }
}
