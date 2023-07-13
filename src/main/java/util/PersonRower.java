package util;

import org.springframework.jdbc.core.RowMapper;
import ru.nikon.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRower implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setId(rs.getInt("id"));
        person.setName(rs.getString("name"));
        person.setBirthYear(rs.getInt("birth_year"));
        return person;
    }
}
