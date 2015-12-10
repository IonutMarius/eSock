package ro.esock.model.persistance.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ro.esock.model.persistance.entitiy.User;
import ro.esock.model.persistance.repository.UserRepository;

@Repository
public class UserJpaRepositoryImpl implements UserRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void create(User user) {
		entityManager.persist(user);
	}

}
