
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
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
public class ServiceList extends ArrayList<Service> {

//    public Service getService(){
//        return this.getService();
//    }
    public void displayAll() {
        for (Service i : this) {
            System.out.println(i);
        }
    }

    public ArrayList<Service> searchName(String findname) {
        ArrayList<Service> result = new ServiceList();
        for (Service s : this) {
            if (s.getName().contains(findname)) {
                result.add(s);
            }
        }
        return result;
    }

    public Service searchById(int findid) {
        Service result = null;
        for (Service s : this) {
            if (s.getId() == findid) {
                result = s;
                break;
            }
        }
        return result;
    }

    public boolean updateService(int updateid) {
        Service result = searchById(updateid);
        if (result != null) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter a new service's name: ");
            result.setName(sc.nextLine());
            System.out.print("Enter a new service's id: ");
            result.setId(sc.nextInt());
            return true;
        }
        return false;
    }

    public void sortByPrice() {
        Collections.sort(this);
    }

    public void sortByName() {
        Collections.sort(this, Service.tmp);
    }

    //ham nay de doc text file va save 
    public void readData(String fileName) {
        FileReader f = null;
        BufferedReader bf = null;
        
        try {
            f = new FileReader(fileName);
            bf = new BufferedReader(f); 
            while(bf.ready()){
                String s = bf.readLine();
                String [] arr = s.split(s);
                if (arr.length == 3) {
                    int id = Integer.parseInt(arr[0].trim());
                    int price = Integer.parseInt(arr[2].trim());
                    String name = arr[1];
                    Service ser = new Service(id, name, price);
                    this.add(ser);
                }
            }
        } catch (Exception e) {
            System.out.println("Loi file");
        }
        
        finally {
            try {
                if (f!=null)f.close();
                if (bf!=null)bf.close();
            } catch (Exception e) {
                System.out.println("Loi dong file");
            }
        }
    }
    
    public void writeData (String filename){
        PrintWriter w = null;
        try {
            w = new PrintWriter(filename);
            for (Service s : this) {
                String tmp = s.getId()+","+s.getName()+","+s.getPrice();
                w.print(tmp);
                w.flush();
            }
        } catch (Exception e) {
            System.out.println("Loi file");
        }
        finally{
            try {
                if (w!=null) w.close();
            } catch (Exception e) {
                System.out.println("Loi dong file");
            }
        }
    }
    
    public void saveBinaryData(String filename){
        FileOutputStream f = null;
        ObjectOutputStream of = null;
        try {
            f = new FileOutputStream(filename);
            of = new ObjectOutputStream(f);
            of.writeObject(this); //ghi cac service vao buffer cua bien of
            //dong len tren chi run duoc neu kich hoat interface Serizable cuar java.io
            //interface Serizable chua ham thu vien cua java, muc dich de convert object -> mang byte 
        } catch (Exception e) {
            System.out.println("Loi file");
        }
        finally{
            try {
                if (f!= null) f.close();
                if (of!=null) of.close();
            } catch (Exception e) {
                System.out.println("Dong file loi");
            }
        }
    }
    
    public void loadBinaryData (String filename) {
        FileInputStream f = null;
        ObjectInputStream ob = null;
        try {
            File test = new File(filename);//lop de check filename co ton tai trong may tinh ko
            if (test.exists()){
                f = new FileInputStream(filename);
                ob = new ObjectInputStream(f);
                this.addAll( (ArrayList<Service>)ob.readObject() );
            } else {
                System.out.println("List of service dang trong, user hay add them bang chuc nang add service");
            }
        } catch (Exception e) {
            System.out.println("loi file");
        }
        finally{
            try {
                if (f!= null) f.close();
                if (ob!=null) ob.close();
            } catch (Exception e) {
                System.out.println("Loi dong file ");
            }
        }
    }
}
