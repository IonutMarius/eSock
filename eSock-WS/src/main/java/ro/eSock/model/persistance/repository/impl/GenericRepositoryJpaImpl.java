package ro.esock.model.persistance.repository.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ro.esock.model.persistance.repository.GenericRepository;

public abstract class GenericRepositoryJpaImpl<T, PK extends Serializable>
		implements GenericRepository<T, PK> {

	@PersistenceContext
	protected EntityManager entityManager;

	protected Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public GenericRepositoryJpaImpl() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

	@Override
	public T findById(PK id) {
		T entity = entityManager.find(entityClass, id);
		return entity;
	}

	@Override
	public T save(T t) {
		entityManager.persist(t);
		return t;
	}
	
	@Override
	public T update(T t) {
		return entityManager.merge(t);		
	}

	@Override
	public void remove(T t) {
		entityManager.remove(t);
	}
}
