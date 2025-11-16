package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    Connection connection;

    public DepartmentDaoJDBC(Connection connection){
        this.connection = connection;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement statement = null;
        try {

            statement = connection.prepareStatement(
                    "INSERT INTO department " +
                        "(Name) " +
                        "VALUES " +
                        "(?)", Statement.RETURN_GENERATED_KEYS
            );

            statement.setString(1,obj.getName());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected>0){
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()){
                    obj.setId(resultSet.getInt(1));
                }
                DB.closeResultset(resultSet);
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(statement);
        }
    }

    @Override
    public void update(Department obj) {

        PreparedStatement statement = null;
        try{

            statement = connection.prepareStatement(
                    "UPDATE department " +
                        "SET Name = ?" +
                        "WHERE Id = ?"
            );

            statement.setString(1, obj.getName());
            statement.setInt(2,obj.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(statement);
        }

    }

    @Override
    public void deleteById(Integer id) {

        PreparedStatement statement = null;
        try{

            statement = connection.prepareStatement(
                    "DELETE FROM department WHERE Id = ?"
            );

            statement.setInt(1,id);
            statement.executeUpdate();

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(statement);
        }

    }

    @Override
    public Department findById(Integer id) {

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{

            statement = connection.prepareStatement(
                "SELECT * FROM department WHERE Id = ?"
            );

            statement.setInt(1,id);
            resultSet = statement.executeQuery();

            if (resultSet.next()){
                return instaciateDepartment(resultSet);
            }
            return null;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(statement);
            DB.closeResultset(resultSet);
        }
    }

    @Override
    public List<Department> findAll() {

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{

            statement = connection.prepareStatement(
                    "SELECT * FROM department"
            );

            resultSet = statement.executeQuery();

            List<Department> list = new ArrayList<>();
            while (resultSet.next()){
                list.add(instaciateDepartment(resultSet));
            }
            return list;

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(statement);
            DB.closeResultset(resultSet);
        }
    }

    private Department instaciateDepartment(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getInt("Id"));
        department.setName(resultSet.getString("Name"));
        return department;
    }
}
