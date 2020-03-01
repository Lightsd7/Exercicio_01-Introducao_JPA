package br.com.fiap.jpa.model.dao;

import java.util.List;

public interface DAO {

	void persist(Object o);
	void merge(Object o);
	void remove(Object o);
	void removeById(final int id);
	List<Object> findAll();
	Object getById(final int id);
	
}
