package br.com.sample.solutionbto.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Repository;

import br.com.sample.solutionbto.entity.Person;

@Repository
public class PersonJbdcDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	class PersonRowMapper implements RowMapper<Person>{

		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person person=new Person();
			person.setId(rs.getInt("ID"));
			person.setName(rs.getString("NAME"));
			person.setLocation(rs.getString("LOCATION"));
			person.setBirthDate(rs.getTimestamp("BIRTH_DATE"));
			return person;
		}
		
	}
	
	public void executeSqlScript()throws SQLException, ScriptException, IOException{
		Connection connection  =jdbcTemplate.getDataSource().getConnection();
        try {
            connection.setAutoCommit(true);
            ScriptUtils.executeSqlScript(connection, new ClassPathResource("data.sql"));
            connection.commit();
       } catch (SQLException e) {
           connection.rollback();
       }finally{
           connection.close();
       }
    }
	
	@SuppressWarnings("all")
	public List<Person> findAll() {
		return jdbcTemplate.query("select * from person", 
				new PersonRowMapper());
	}
	
	@SuppressWarnings("all")
	public Person findById(Integer id) {
		return (Person) jdbcTemplate.queryForObject(
				"select * from person where id=?", 
				new Object[] {id},
				new PersonRowMapper());//new BeanPropertyRowMapper(Person.class));
	}
	
	public int insert(Person person) {
		if(person == null)
			return -1;
		else
			return jdbcTemplate.update(
					"INSERT INTO person (ID, NAME, LOCATION, BIRTH_DATE ) "
					+ "VALUES(?, ?, ?, ?)", 
					new Object[] {
							person.getId(), 
							person.getName(), 
							person.getLocation(),
							new Timestamp(person.getBirthDate().getTime())
					});
	}
	
	public int update(Person person) {
		if(person == null)
			return -1;
		else
			return jdbcTemplate.update(
					"UPDATE person SET NAME=? , LOCATION=?, BIRTH_DATE=?  "
					+ " WHERE ID=? ", 
					new Object[] {
							person.getName(), 
							person.getLocation(),
							new Timestamp(person.getBirthDate().getTime()),
							person.getId()
					});
	}
	
	
	@SuppressWarnings("all")
	public int delete(Integer id) {
		return jdbcTemplate.update(
				"delete from person where id=?", 
				new Object[] {id});
	}
}