package ro.esock.model.domain.service;

import java.io.Serializable;
import java.util.List;

public interface GenericService<T, PK extends Serializable> {
	T findById(PK id);
	List<T> findAll();
	T create(T t);
	T update(T t);
	void remove(T t);
	Integer count();
}
