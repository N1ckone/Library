package ru.nikon.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.nikon.models.Book;
import ru.nikon.models.Person;

import java.sql.Types;
import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooks() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Optional selectBook(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id = ?", new Object[]{id},
                new int[]{Types.INTEGER}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
    }

    public Optional getBookOwner(String bookName) {
        return jdbcTemplate.query("SELECT Person.* FROM Person JOIN BOOK on person.id = book.person_id WHERE book.name = ?",
                new Object[]{bookName}, new int[]{Types.VARCHAR}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
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

    public void appoint(int id, int personId) {
        jdbcTemplate.update("UPDATE Book SET person_id = ? WHERE id = ?", personId, id);
    }

    public void leave(int id) {
        jdbcTemplate.update("UPDATE Book SET person_id = null WHERE id = ?", id);
    }
}
