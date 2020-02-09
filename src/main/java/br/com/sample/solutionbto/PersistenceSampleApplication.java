package br.com.sample.solutionbto;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.sample.solutionbto.entity.Person;
import br.com.sample.solutionbto.jdbc.PersonJbdcDao;

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
	private PersonJbdcDao personJbdcDao;
	
	public static void main(String[] args) {
		SpringApplication.run(PersistenceSampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("All users -> {}",personJbdcDao.findAll());
		Person person10001 = personJbdcDao.findById(10001);
		logger.info("Find by id=10001 -> {}", person10001);
		logger.info("Delete user by id=10002, number of lines deleted -> {}",personJbdcDao.delete(10002));
		
		Person person10004=new Person(10004, "Roberto", "XPTO", new Date());
		logger.info("Insert user by id=10004, number of lines inserted -> {}",personJbdcDao.insert(person10004));
		logger.info("Find by id={} -> {}", person10004.getId(), personJbdcDao.findById(person10004.getId()));
		
		person10004.setName("Roberto Silva");
		person10004.setLocation("São Paulo");
		person10004.setBirthDate(new Date());
		logger.info("Update user by id=10004, number of lines updated -> {}",personJbdcDao.update(person10004));
		logger.info("Find by id={} -> {}", person10004.getId(), personJbdcDao.findById(person10004.getId()));
	}
}
