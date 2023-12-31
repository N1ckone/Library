package ru.nikon.util;

import org.springframework.jdbc.core.RowMapper;
import ru.nikon.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRower implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setName(rs.getString("name"));
        book.setAuthor(rs.getString("author"));
        book.setYear(rs.getInt("year"));
        return book;
    }
}
