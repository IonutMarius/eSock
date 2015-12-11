package ro.esock.model.persistance.service;

import java.io.Serializable;

public interface GenericService<T, PK extends Serializable> {
	T findById(PK id);
	T save(T t);
	T update(T t);
	void remove(T t);
}
