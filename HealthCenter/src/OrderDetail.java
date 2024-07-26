/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TanHuynh
 */
public class OrderDetail {

    private Pet pet;
    private Service service;

    public OrderDetail() {
        pet = new Pet();
        service = new Service();

    }

    public OrderDetail(Pet pet, Service service) {
        this.pet = pet;
        this.service = service;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Service getService() {
        return service;
    }

    public int getSerID () {
        return this.service.getId();
    }
    
    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "pet=" + pet + ", service=" + service + '}';
    }

}
