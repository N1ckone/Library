package ru.nikon.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.nikon.models.Book;
import ru.nikon.models.Person;
import util.PersonRower;

import java.sql.Types;
import java.util.List;
import java.util.Optional;

@Component
public class LibraryDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LibraryDAO(JdbcTemplate jdbcTemplate) {
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

    public List<Book> getBooks() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Optional selectBook(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id = ?", new Object[]{id},
                new int[]{Types.INTEGER}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
    }

    public void insertBook(Book book) {
        jdbcTemplate.update("INSERT INTO Book(name, author, year) VALUES(?,?,?)",
               book.getName(), book.getAuthor(), book.getYear());
    }

    public void updateBook(Book book, int id) {
        jdbcTemplate.update("UPDATE Book SET name = ?, author = ?, year = ? WHERE id = ?",
                book.getName(), book.getAuthor(), book.getYear(), id);
    }

    public void deleteBook(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id = ?", id);
    }

}
