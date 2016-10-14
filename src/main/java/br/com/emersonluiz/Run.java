package br.com.emersonluiz;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.emersonluiz.model.Person;
import br.com.emersonluiz.repository.ConnectionFactory;
import br.com.emersonluiz.repository.PersonRepository;

public class Run {

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = ConnectionFactory.createConnection("helloworld");
            PersonRepository personRepository = new PersonRepository(connection);
            Person person = new Person("Emerson", "Castro");
            Person p1 = personRepository.create(person);
            System.out.println("Person Insert: " + p1);

            Person p2 = personRepository.findOne(p1.getId());
            System.out.println("Person Find One: " + p2);

            p2.setLastName("Luiz");
            personRepository.update(p2.getId(), p2);
            Person p3 = personRepository.findOne(p2.getId());
            System.out.println("Person Update: " + p3);

            personRepository.delete(p3.getId());

            List<Person> list = personRepository.findAll();
            list.forEach(p4 -> System.out.println("Person Update: " + p4));
        } catch (Exception e) {
            System.out.printf("Error: %s", e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.printf("Error: %s", e.getMessage());
            }
        }
    }

}
