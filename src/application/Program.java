package application;

import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class Program {
    public static void main(String[] args) {
        Department dpt = new Department(1,"Books");
        System.out.println(dpt);

        Seller seller = new Seller(21,"Bob","Bob@gmail.com",new Date(),3000.00,dpt);
        System.out.println(seller);
    }
}
