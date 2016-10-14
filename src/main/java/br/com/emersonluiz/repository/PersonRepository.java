package br.com.emersonluiz.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.emersonluiz.model.Person;

public class PersonRepository implements Crud<Person> {

    Connection connection;

    public PersonRepository(Connection connection) {
        this.connection = connection;
    }

    public Person create(Person person) throws Exception {
        try {
            PreparedStatement ps = this.connection.prepareStatement("INSERT INTO person (first_name, last_name) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, person.getFirstName());
            ps.setString(2, person.getLastName());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                System.out.println("Inserted!!!");
                person.setId(rs.getInt(1));
            }
            return person;
        } catch (SQLException e) {
            System.out.printf("Create Error: %s", e.getMessage());
            throw e;
        }
    }

    public Person findOne(int id) throws Exception {
        try {
            PreparedStatement ps = this.connection.prepareStatement("SELECT id, first_name, last_name FROM person where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Person person = null;
            if (rs.next()) {
                person = new Person();
                person.setId(rs.getInt(1));
                person.setFirstName(rs.getString(2));
                person.setLastName(rs.getString(3));
            }
            return person;
        } catch (SQLException e) {
            System.out.printf("Find One Error: %s", e.getMessage());
            throw e;
        }
    }

    public void update(int id, Person person) {
        try {
            PreparedStatement ps = this.connection.prepareStatement("UPDATE person SET first_name = ?, last_name = ? WHERE id = ?");
            ps.setString(1, person.getFirstName());
            ps.setString(2, person.getLastName());
            ps.setInt(3, id);
            ps.execute();
            System.out.println("Updated!!!");
        } catch (SQLException e) {
            System.out.printf("Error: %s", e.getMessage());
        }
    }

    public void delete(int id) throws Exception {
        try {
            PreparedStatement ps = this.connection.prepareStatement("DELETE FROM person where id = ?");
            ps.setInt(1, id);
            ps.execute();
            System.out.println("Deleted!!!");
        } catch (SQLException e) {
            System.out.printf("Delete Error: %s", e.getMessage());
            throw e;
        }
    }

    public List<Person> findAll() throws SQLException {
        try {
            PreparedStatement ps = this.connection.prepareStatement("SELECT id, first_name, last_name FROM person");
            ResultSet rs = ps.executeQuery();
            List<Person> list = new ArrayList<Person>();
            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt(1));
                person.setFirstName(rs.getString(2));
                person.setLastName(rs.getString(3));
                list.add(person);
            }
            return list;
        } catch (SQLException e) {
            System.out.printf("Find All Error: %s", e.getMessage());
            throw e;
        }
    }

}
