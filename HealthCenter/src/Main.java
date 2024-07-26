
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author TanHuynh
 */
public class Main {

    public static void main(String[] args) {
        int choice = 0;
        String fileName = "data.dat"; //"testne.txt";
        CustomerSet custset = new CustomerSet();
        ServiceList listOServiceList = new ServiceList();
        listOServiceList.loadBinaryData(fileName);
//        listOServiceList.readData(fileName);
//        listOServiceList.add(new Service(100, "cat mong", 50));
//        listOServiceList.add(new Service(101, "mat xa", 500));
//        listOServiceList.add(new Service(102, "tam bon", 150));
//        listOServiceList.add(new Service(103, "tia long", 250));

        do {
            System.out.println("1. manage service:");
            System.out.println("2. manage customer and orther");
            System.out.println("3. report");
            System.out.print("Ener choice:");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    int choice2 = 0;
                    do {
                        System.out.println("1. add a new service");
                        System.out.println("2. display all services");
                        System.out.println("3. update service");
                        System.out.println("4. search service");
                        System.out.print("Enter choice:");
                        choice2 = sc.nextInt();
                        switch (choice2) {
                            case 1:
                                int id,
                                 price;
                                String name;
                                System.out.println("enter id:");
                                id = sc.nextInt();
                                System.out.println("enter name:");
                                sc = new Scanner(System.in);
                                name = sc.nextLine();
                                System.out.println("enter price");
                                price = sc.nextInt();
                                Service s = new Service(id, name, price);
                                if (listOServiceList.searchById(id) == null && listOServiceList.add(s)) {
                                    System.out.println("Added");
                                    //listOServiceList.writeData(fileName);
                                    listOServiceList.saveBinaryData(fileName);
                                } else {
                                    System.out.println("add fail!!!");
                                }
                                
                                break;
                            case 2:
                                System.out.println("1.sort by price");
                                System.out.println("2.sort by name");
                                System.out.print("Enter choice:");
                                sc = new Scanner(System.in);
                                int choice5 = sc.nextInt();
                                if (choice5 == 1) {
                                    listOServiceList.sortByPrice();
                                } else {
                                    listOServiceList.sortByName();
                                }
                                listOServiceList.displayAll();
                                break;

                            case 3:
                                int updateid;
                                System.out.println("Enter id to update");
                                updateid = sc.nextInt();
                                boolean result4 = listOServiceList.updateService(updateid);
                                if (result4) {
                                    System.out.println("Updated !!!");
                                    //listOServiceList.writeData(fileName);
                                    listOServiceList.saveBinaryData(fileName);
                                } else {
                                    System.out.println("No update !!!");
                                }
                                break;
                            case 4:
                                System.out.println("1. search by name");
                                System.out.println("2. search by id");
                                System.out.print("Enter choice(1 or 2):");
                                int choice3 = sc.nextInt();
                                if (choice3 == 1) {
                                    sc = new Scanner(System.in);
                                    System.out.print("Enter name to find:");
                                    String findname = sc.nextLine();
                                    ArrayList<Service> result = listOServiceList.searchName(findname);
                                    if (result.isEmpty()) {
                                        System.out.println("not found");
                                    } else {
                                        System.out.println("danh sach service tim thay");
                                        ((ServiceList) result).displayAll();
                                    }
                                } else if (choice3 == 2) {
                                    System.out.print("Enter id to find:");
                                    int findid = sc.nextInt();
                                    Service result2 = listOServiceList.searchById(findid);
                                    if (result2 == null) {
                                        System.out.println("Not found !!!");
                                    } else {
                                        System.out.println("tim thay 1 service");
                                        System.out.println(result2);
                                    }
                                    break;
                                }
                        }
                    } while (choice2 <= 4);
                case 2: // manage customer and order
                    int choice3 = 0;
                    do {
                        System.out.println("1. Add a new customer.");
                        System.out.println("2. display customer.");
                        System.out.println("3. search customer by id.");
                        System.out.print("Enter choice:");
                        choice3 = sc.nextInt();
                        switch (choice3) {
                            case 1:
                                Customer cust = new Customer();
                                String cont = "y";
                                do {
                                    System.out.println("Them thong tin pet cho khach hang");
                                    System.out.print("Enter pet id:");
                                    int petid = sc.nextInt();
                                    System.out.print("Enter pet name:");
                                    sc = new Scanner(System.in);
                                    String petname = sc.nextLine();
                                    Pet p = new Pet(petid, petname);
                                    if (cust.addPet(p)) {
                                        System.out.println("Added pet !!!!! ");
                                    }
                                    System.out.print("add pet anymore(y/n)?: ");
                                    sc = new Scanner(System.in);
                                    cont = sc.nextLine();
                                } while (cont.equalsIgnoreCase("y"));
                                String ans = "y";
                                do {
                                    System.out.println("Quy trinh tao order:");
                                    Order ord = new Order();
                                    cont = "y";
                                    do {
                                        System.out.println("chon pet theo danh sach pet cua ban");
                                        cust.displayPetlist();
                                        System.out.println("Enter pet id to order");
                                        int petId_order = sc.nextInt();
                                        Pet pet = cust.searchPet(petId_order);
                                        System.out.println("DS service cua he thong");
                                        listOServiceList.displayAll();
                                        System.out.println("Enter service id to order");
                                        int serviceId_order = sc.nextInt();
                                        Service service = listOServiceList.searchById(serviceId_order);
                                        if (pet != null && service != null) {
                                            OrderDetail detail = new OrderDetail(pet, service);
                                            ord.addDetail(detail);
                                            System.out.println("Added detail !!!");
                                        } else {
                                            System.out.println("Enter petid sai or serviceid sai!!!");
                                        }
                                        System.out.println("Add detail anymore(y/n)?: ");
                                        sc = new Scanner(System.in);
                                        cont = sc.nextLine();
                                    } while (cont.equalsIgnoreCase("y"));
                                    if (cust.addOrder(ord)) {
                                        System.out.println("add 1 order !!!");
                                    }
                                    System.out.println("Add order anymore(y/n)?: ");
                                    sc = new Scanner(System.in);
                                    ans = sc.nextLine();
                                } while (ans.equalsIgnoreCase("y"));
                                System.out.println("Thong tin khach hang vua tao");
                                cust.output();
                                custset.add(cust); // ham chi run khi co ham compareto
                                break;
                            case 2:
                                System.out.println("Tap KH cua he thong");
                                custset.displayAll();
                                break;
                            case 3:
                                System.out.print("Enter cust'd id to find:");
                                int custId = sc.nextInt();
                                Customer rs = custset.searchById(custId);
                                if (rs != null) {
                                    System.out.println("thay KH");
                                    rs.output();
                                } else {
                                    System.out.println("not found");
                                }
                                break;
                        }
                    } while (choice3 <= 3);
                    break;
                case 3:
                    int choice4 = 0;
                    do {
                        System.out.println("1. Hien thi so khach hang cua he thong");
                        System.out.println("2. Hien thi khach hang co nhieu don hang nhat");
                        System.out.println("3. Hien thi ddh cua cac khach hang da dat (su dung) trong nam");
                        System.out.println("4. Service duoc su dung nhieu nhat");
                        System.out.println("5. Service chua co ai su dung");
                        System.out.println("6. Hien thi doanh thu cua he thong theo quy");
                        System.out.println("7. Exit");
                        System.out.printf("Enter your choice: ");
                        sc = new Scanner(System.in);
                        choice4 = sc.nextInt();

                        switch (choice4) {
                            case 1:
                                custset.displayAll();
                                break;
                            case 2:
                                custset.displayCustomerBestOder().displayOrderlist();
                                break;
                            case 3:
                                System.out.print("Enter year:");
                                int year = sc.nextInt();
                                ArrayList<Customer> the_order = custset.getCustOrderByYear(year);
                                for (Customer t : the_order) {
                                    t.output();
                                }
                                break;
                            case 4:
                                custset.displayBestUsedServices();
                                break;
                            case 5:
                                custset.displaySmallistUsedServices();
                                break;
                            case 6:
                                System.out.print("Enter year:");
                                int years = sc.nextInt();
                                long[] revenueByQuarter = custset.getRevenueByQuarter(years);
                                for (int quarter = 0; quarter < 4; quarter++) {
                                    System.out.println("Q" + (quarter + 1) + ": " + revenueByQuarter[quarter] + " VND");
                                }
                                break;
                        }
                    } while (choice4 < 7);
                    break;
            }
        } while (choice <= 3);
    }
}
