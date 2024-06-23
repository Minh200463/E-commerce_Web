package edu.poly.asm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.poly.asm.model.Categories;

public interface CategoryService {

	void deleteAll();

	void deleteAll(Iterable<? extends Categories> entities);

	void deleteAllById(Iterable<? extends Integer> ids);

	void delete(Categories entity);

	Categories getById(Integer id);

	void deleteById(Integer id);

	long count();

	void deleteAllInBatch();

	<S extends Categories> boolean exists(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	boolean existsById(Integer id);

	void deleteAllInBatch(Iterable<Categories> entities);

	Optional<Categories> findById(Integer id);

	List<Categories> findAllById(Iterable<Integer> ids);

	List<Categories> findAll();

	<S extends Categories> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Categories> S saveAndFlush(S entity);

	Page<Categories> findAll(Pageable pageable);

	List<Categories> findAll(Sort sort);

	<S extends Categories> List<S> saveAll(Iterable<S> entities);

	<S extends Categories> S save(S entity);

	
}
