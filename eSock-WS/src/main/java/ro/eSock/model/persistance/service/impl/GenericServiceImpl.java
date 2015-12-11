package ro.esock.model.persistance.service.impl;

import java.io.Serializable;

import ro.esock.model.persistance.repository.GenericRepository;
import ro.esock.model.persistance.service.GenericService;

public abstract class GenericServiceImpl<T, PK extends Serializable> implements GenericService<T, PK>{
	
	protected abstract GenericRepository<T, PK> getRepository();

	@Override
	public T findById(PK id) {
		return getRepository().findById(id);
	}

	@Override
	public T save(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T update(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(T t) {
		// TODO Auto-generated method stub
		
	}

}
