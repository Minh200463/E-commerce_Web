package edu.poly.asm.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import edu.poly.asm.model.Orderdetails;

public interface OrderdetailsService {

	void deleteAll();

	<S extends Orderdetails> List<S> findAll(Example<S> example, Sort sort);

	<S extends Orderdetails> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends Orderdetails> entities);

	Orderdetails getReferenceById(Integer id);

	void deleteAllById(Iterable<? extends Integer> ids);

	void delete(Orderdetails entity);

	Orderdetails getById(Integer id);

	void deleteById(Integer id);

	long count();

	<S extends Orderdetails, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	Orderdetails getOne(Integer id);

	void deleteAllInBatch();

	<S extends Orderdetails> boolean exists(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	<S extends Orderdetails> long count(Example<S> example);

	boolean existsById(Integer id);

	void deleteAllInBatch(Iterable<Orderdetails> entities);

	Optional<Orderdetails> findById(Integer id);

	<S extends Orderdetails> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<Orderdetails> entities);

	List<Orderdetails> findAllById(Iterable<Integer> ids);

	List<Orderdetails> findAll();

	<S extends Orderdetails> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Orderdetails> S saveAndFlush(S entity);

	Page<Orderdetails> findAll(Pageable pageable);

	void flush();

	List<Orderdetails> findAll(Sort sort);

	<S extends Orderdetails> Optional<S> findOne(Example<S> example);

	<S extends Orderdetails> List<S> saveAll(Iterable<S> entities);

	<S extends Orderdetails> S save(S entity);

}
