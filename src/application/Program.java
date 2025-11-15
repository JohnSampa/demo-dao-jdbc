package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;


public class Program {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("---- TEST 1: findById ----");

        Seller seller = sellerDao.findById(1);

        System.out.println(seller);

        System.out.println();
        System.out.println("---- TEST 2: findByDepartment ----");
        sellerDao.findByDepartment(seller.getDepartment()).forEach(System.out::println);

    }
}
