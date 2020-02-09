package br.com.sample.solutionbto.jdbc;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.sample.solutionbto.entity.Person;

@Repository
public class PersonJbdcDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("all")
	public List<Person> findAll() {
		return jdbcTemplate.query("select * from person", 
				new BeanPropertyRowMapper(Person.class));
	}
	
	@SuppressWarnings("all")
	public Person findById(Integer id) {
		return (Person) jdbcTemplate.queryForObject(
				"select * from person where id=?", 
				new Object[] {id},
				new BeanPropertyRowMapper(Person.class));
	}
	
	public int insert(Person person) {
		if(person == null)
			return -1;
		else
			return jdbcTemplate.update(
					"INSERT INTO PERSON (ID, NAME, LOCATION, BIRTH_DATE ) "
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
					"UPDATE PERSON SET NAME=? , LOCATION=?, BIRTH_DATE=?  "
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