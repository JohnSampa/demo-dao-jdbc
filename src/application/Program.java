package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

import java.util.Date;
import java.util.Objects;


public class Program {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("---- TEST 1: findById ----");

        Seller seller = sellerDao.findById(1);

        System.out.println(seller);

        System.out.println();
        System.out.println("---- TEST 2: findByDepartment ----");
        sellerDao.findByDepartment(seller.getDepartment()).forEach(System.out::println);

        System.out.println();
        System.out.println("---- TEST 3: findAll ----");
        sellerDao.findALl().forEach(System.out::println);

        System.out.println();
        System.out.println("---- TEST 4: insert ----");
        sellerDao.insert(new Seller(
                null,
                "Jonathan Sampaio",
                "aurijona@email.com",
                new Date(),
                30000.00,
                seller.getDepartment()
        ));
        System.out.println("Inserted!");
        sellerDao.findALl().stream().filter(x -> Objects.equals(x.getName(), "Jonathan Sampaio")).forEach(System.out::println);

        System.out.println();
        System.out.println("---- TEST 5: update ----");
        seller = sellerDao.findById(19);
        seller.setName("Jonathan Sampaio Samp");
        seller.setBaseSalary(50000.00);
        sellerDao.update(seller);
        sellerDao.findALl().stream().filter(x -> Objects.equals(x.getName(),"Jonathan Sampaio Samp")).forEach(System.out::println);

        System.out.println();
        System.out.println("---- TEST 5: deleteById----");
        sellerDao.deleteById(20);
        System.out.println("Delete successful");
        sellerDao.findALl().forEach(System.out::println);

    }
}
