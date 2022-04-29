package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    private final Connection connection;

    public UserDaoJdbcImpl() throws SQLException {
        connection = new Util().getConnection();

    }

    public void createUsersTable() {
        String user = """
                create table if not exists users (
                id serial primary key,
                name varchar(50) not null,
                lastName varchar(50) not null,
                age smallint not null
                );
                """;
        try(Statement statement =  connection.createStatement()){
            statement.execute(user);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        String query = """
                drop table users
                """;
        try (Statement statement = connection.createStatement()) {
            statement.executeQuery(query);
            System.out.println("You have successfully deleted table!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        }


    public void saveUser(String name, String lastName, byte age) {
       // User user = new User(name,lastName,age);
        String query = """
                insert into users(name,lastName,age) values (?,?,?);
                """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,lastName);
        preparedStatement.setByte(3, age);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User removeUserById(long id) {
        String query = "delete from students where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public List<User> getAllUsers() {
        String query = "select * from users";
        List<User> allUser = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString("name"));
                user.setAge(resultSet.getByte(3));
                allUser.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allUser;

    }

    public void cleanUsersTable() {
        String query = "truncate table users";
        try (Statement statement = connection.createStatement()) {
            statement.executeQuery(query);
            System.out.println(" you have successfully deleted all students in students table");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean existsByFirstName(String firstName) {
        String query = """
            select case when name notnull then true else false end from users where name = ?;
            """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, firstName );
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}