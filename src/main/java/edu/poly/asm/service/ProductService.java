package edu.poly.asm.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import edu.poly.asm.model.Products;

public interface ProductService {

	void deleteAll();

	<S extends Products> List<S> findAll(Example<S> example, Sort sort);

	<S extends Products> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends Products> entities);

	Products getReferenceById(Integer id);

	void deleteAllById(Iterable<? extends Integer> ids);

	void delete(Products entity);

	Products getById(Integer id);

	void deleteById(Integer id);

	long count();

	<S extends Products, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	Products getOne(Integer id);

	void deleteAllInBatch();

	<S extends Products> boolean exists(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	<S extends Products> long count(Example<S> example);

	boolean existsById(Integer id);

	void deleteAllInBatch(Iterable<Products> entities);

	Optional<Products> findById(Integer id);

	<S extends Products> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<Products> entities);

	List<Products> findAllById(Iterable<Integer> ids);

	List<Products> findAll();

	<S extends Products> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Products> S saveAndFlush(S entity);

	Page<Products> findAll(Pageable pageable);

	void flush();

	List<Products> findAll(Sort sort);

	<S extends Products> Optional<S> findOne(Example<S> example);

	<S extends Products> List<S> saveAll(Iterable<S> entities);

	<S extends Products> S save(S entity);

	

}
