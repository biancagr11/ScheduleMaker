package repo;

import domain.Employee;
import domain.Entity;
import domain.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmployeeDatabaseRepository extends EmployeeDatabaseRepositoryInterface<Integer, Employee> {

    private String url;
    private String username;
    private String password;

    public EmployeeDatabaseRepository(String url, String username, String password) {
        this.url = url;
        this.username=username;
        this.password=password;
    }

    @Override
    public Iterable<Employee> findAll() {
        List<Employee> employees=new ArrayList<>();
        try (
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees");
                ResultSet resultSet = statement.executeQuery();
        ) {

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String job=resultSet.getString("job");
                employees.add(new Employee(id, firstName, lastName, Job.valueOf(job)));

            }
        } catch (
                SQLException ex) {
            ex.printStackTrace();
        }
        return employees;
    }
    @Override
    public Employee save(Employee entity){

            try (Connection connection = DriverManager.getConnection(url, username, password);
                 PreparedStatement statement = connection.prepareStatement("INSERT INTO employees(firstName,lastName,job) VALUES(?,?,?)");
            ) {
                statement.setString(1, entity.getFirstName());
                statement.setString(2, entity.getLastName());
                statement.setString(3, entity.getJob().name());
                statement.execute();
            }catch(SQLException ex) {
                ex.printStackTrace();
                return null;
            }
        return entity;
    }

    @Override
    public void delete(Integer id) {
            try(Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement statement = connection.prepareStatement("DELETE FROM employees WHERE id = ?");
            ){
                statement.setInt(1, Integer.parseInt(id.toString()));
                statement.executeUpdate();
            }catch(SQLException ex) {
                ex.printStackTrace();
            }
    }

    @Override
    public Employee findOne(Integer id) {
        try (
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees WHERE id="+id);
                ResultSet resultSet = statement.executeQuery();
        ) {

            resultSet.next();
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String job=resultSet.getString("job");
            return new Employee(id, firstName, lastName, Job.valueOf(job));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

