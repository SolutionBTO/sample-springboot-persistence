package br.com.sample.solutionbto.jdbc;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sample.solutionbto.entity.Person;

@Repository
@Transactional
public class PersonJpaRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Person findById(Integer id) {
		return this.entityManager.find(Person.class, id);
	}
	
	public Person insert(Person person) {
		return this.entityManager.merge(person);
	}
	
	public Person update(Person person) {
		return this.entityManager.merge(person);
	}
	
	public void delete(Integer id) {
		Person person = this.findById(id);
		this.entityManager.remove(person);
	}
}
