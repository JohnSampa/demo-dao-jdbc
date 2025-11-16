package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {
    public static void main(String[] args) {

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("===== Test 1 : insert =====");
        Department department =  new Department(null, "Music");
        departmentDao.insert(department);

        System.out.println();
        System.out.println("===== Test 2 : findAll =====");
        departmentDao.findAll().forEach(System.out::println);

        System.out.println();
        System.out.println("===== Test 3 : update =====");
        department.setName("Food");
        departmentDao.update(department);
        departmentDao.findAll().forEach(System.out::println);

        System.out.println();
        System.out.println("===== Test 4 : findById =====");
        System.out.println(departmentDao.findById(3));;

        System.out.println();
        System.out.println("===== Test 5 : delete =====");
        departmentDao.deleteById(department.getId());
        departmentDao.findAll().forEach(System.out::println);

    }
}
