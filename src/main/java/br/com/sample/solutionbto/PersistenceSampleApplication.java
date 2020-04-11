package br.com.sample.solutionbto;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.sample.solutionbto.entity.Person;
import br.com.sample.solutionbto.repository.PersonRepository;

/**
 * basead in course: 
 * https://github.com/in28minutes/spring-master-class/blob/master/04-spring-jdbc-to-jpa/Step05.md
 * @author rosilva
 *
 */
@SpringBootApplication
public class PersistenceSampleApplication implements CommandLineRunner {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PersonRepository personRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(PersistenceSampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		logger.info("All users -> {}",personRepository.findAll());
		Person person10001 = personRepository.findById(10001).orElse(null);
		logger.info("Find by id=10001 -> {}", person10001);
		personRepository.delete(personRepository.findById(10002).orElse(null));
		logger.info("Delete user by id=10002, number of lines deleted -> {}", personRepository.findAll());
		
		Person person10004=new Person(10004, "Roberto", "XPTO", new Date());
		person10004 = personRepository.save(person10004);
		logger.info("Insert user by id=10004, number of lines inserted -> {}", person10004);
		
		person10004.setName("Roberto Silva");
		person10004.setLocation("São Paulo");
		person10004.setBirthDate(new Date());
		person10004 = personRepository.save(person10004);
		logger.info("Update user by id=10004, number of lines updated -> {}", person10004);
		logger.info("Find by id={} -> {}", person10004.getId(), personRepository.findById(person10004.getId()).orElse(null));
	}
}
