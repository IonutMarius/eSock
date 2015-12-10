package ro.esock.model.persistance.repository;

import java.io.Serializable;

public interface GenericRepository<T, PK extends Serializable> {

	T findById(PK id);
	T save(T t);
	T update(T t);
	void remove(T t);
}
