package br.com.sample.solutionbto.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sample.solutionbto.entity.Person;

/**
 * 
 * @author rosilva
 *
 */
@Repository
@Transactional
public interface PersonRepository extends CrudRepository<Person, Integer> {}
