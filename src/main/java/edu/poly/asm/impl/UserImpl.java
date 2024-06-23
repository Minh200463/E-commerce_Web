package edu.poly.asm.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import edu.poly.asm.model.Users;
import edu.poly.asm.repository.UserReponsitory;
import edu.poly.asm.service.UserService;

@Service
public class UserImpl implements UserService {
	
	UserReponsitory userrepository;

	public UserImpl(UserReponsitory userrepository) {
		this.userrepository = userrepository;
	}

	@Override
	public <S extends Users> S save(S entity) {
		return userrepository.save(entity);
	}

	@Override
	public <S extends Users> List<S> saveAll(Iterable<S> entities) {
		return userrepository.saveAll(entities);
	}

	@Override
	public <S extends Users> Optional<S> findOne(Example<S> example) {
		return userrepository.findOne(example);
	}

	@Override
	public List<Users> findAll(Sort sort) {
		return userrepository.findAll(sort);
	}

	@Override
	public void flush() {
		userrepository.flush();
	}

	@Override
	public Page<Users> findAll(Pageable pageable) {
		return userrepository.findAll(pageable);
	}

	@Override
	public <S extends Users> S saveAndFlush(S entity) {
		return userrepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Users> List<S> saveAllAndFlush(Iterable<S> entities) {
		return userrepository.saveAllAndFlush(entities);
	}

	@Override
	public List<Users> findAll() {
		return userrepository.findAll();
	}

	@Override
	public List<Users> findAllById(Iterable<Integer> ids) {
		return userrepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<Users> entities) {
		userrepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Users> Page<S> findAll(Example<S> example, Pageable pageable) {
		return userrepository.findAll(example, pageable);
	}

	@Override
	public Optional<Users> findById(Integer id) {
		return userrepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<Users> entities) {
		userrepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return userrepository.existsById(id);
	}

	@Override
	public <S extends Users> long count(Example<S> example) {
		return userrepository.count(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		userrepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Users> boolean exists(Example<S> example) {
		return userrepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		userrepository.deleteAllInBatch();
	}

	@Override
	public Users getOne(Integer id) {
		return userrepository.getOne(id);
	}

	@Override
	public <S extends Users, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return userrepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return userrepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		userrepository.deleteById(id);
	}

	@Override
	public Users getById(Integer id) {
		return userrepository.getById(id);
	}

	@Override
	public void delete(Users entity) {
		userrepository.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		userrepository.deleteAllById(ids);
	}

	@Override
	public Users getReferenceById(Integer id) {
		return userrepository.getReferenceById(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Users> entities) {
		userrepository.deleteAll(entities);
	}

	@Override
	public <S extends Users> List<S> findAll(Example<S> example) {
		return userrepository.findAll(example);
	}

	@Override
	public <S extends Users> List<S> findAll(Example<S> example, Sort sort) {
		return userrepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		userrepository.deleteAll();
	}
	
	
	
}
