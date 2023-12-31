package ru.nikon.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.nikon.models.Book;
import ru.nikon.models.Person;
import ru.nikon.util.BookRower;
import ru.nikon.util.PersonRower;

import java.sql.Types;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Person> getPeople() {
        return jdbcTemplate.query("SELECT * FROM Person", new PersonRower());
    }

    public Optional selectPerson(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id = ?",
                new Object[]{id}, new int[]{Types.INTEGER},
                new PersonRower()).stream().findAny();
    }

    public Optional selectPerson(String name) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE name = ?",
                new Object[]{name}, new int[]{Types.VARCHAR},
                new PersonRower()).stream().findAny();
    }
    public void insertPerson(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name,birth_year) VALUES(?,?)",
                person.getName(), person.getBirthYear());
    }

    public void updatePerson(Person person, int id) {
        jdbcTemplate.update("UPDATE Person SET name = ?, birth_year = ?" +
                " WHERE id = ?", person.getName(), person.getBirthYear(), id);
    }

    public void deletePerson(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id = ?", id);
    }

    public List<Book> getBooksList(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id = ?",
                new Object[]{id}, new int[]{Types.INTEGER}, new BookRower());
    }
}
