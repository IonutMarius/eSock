package ro.esock.model.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ro.esock.model.repository.GenericRepository;
import ro.esock.model.service.GenericService;

public abstract class GenericServiceImpl<T, PK extends Serializable> implements GenericService<T, PK>{
	
	protected abstract GenericRepository<T, PK> getRepository();

	@Override
	public T findById(PK id) {
		return getRepository().findById(id);
	}
	
	@Override
	public List<T> findAll() {
		return getRepository().findAll();
	}

	@Override
	@Transactional
	public T create(T t) {
		return getRepository().create(t);
	}

	@Override
	@Transactional
	public T update(T t) {
		return getRepository().update(t);
	}

	@Override
	@Transactional
	public void remove(T t) {
		getRepository().remove(t);
	}

	@Override
	public Integer count() {
		return getRepository().count();
	}
}
