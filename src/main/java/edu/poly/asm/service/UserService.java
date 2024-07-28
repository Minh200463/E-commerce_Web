package edu.poly.asm.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import edu.poly.asm.model.Users;

public interface UserService {

	void deleteAll();

	<S extends Users> List<S> findAll(Example<S> example, Sort sort);

	<S extends Users> List<S> findAll(Example<S> example);

	void deleteAll(Iterable<? extends Users> entities);

	Users getReferenceById(Integer id);

	void deleteAllById(Iterable<? extends Integer> ids);

	void delete(Users entity);

	Users getById(Integer id);

	void deleteById(Integer id);

	long count();

	<S extends Users, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	Users getOne(Integer id);

	void deleteAllInBatch();

	<S extends Users> boolean exists(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	<S extends Users> long count(Example<S> example);

	boolean existsById(Integer id);

	void deleteAllInBatch(Iterable<Users> entities);

	Optional<Users> findById(Integer id);

	<S extends Users> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<Users> entities);

	List<Users> findAllById(Iterable<Integer> ids);

	List<Users> findAll();

	<S extends Users> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Users> S saveAndFlush(S entity);

	Page<Users> findAll(Pageable pageable);

	void flush();

	List<Users> findAll(Sort sort);

	<S extends Users> Optional<S> findOne(Example<S> example);

	<S extends Users> List<S> saveAll(Iterable<S> entities);

	<S extends Users> S save(S entity);

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
