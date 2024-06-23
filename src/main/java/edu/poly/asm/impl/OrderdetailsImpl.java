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

import edu.poly.asm.model.Orderdetails;
import edu.poly.asm.repository.OrderdetailsRepository;
import edu.poly.asm.service.OrderdetailsService;

@Service
public class OrderdetailsImpl implements OrderdetailsService {

	OrderdetailsRepository orderdetailsrepository;

	public OrderdetailsImpl(OrderdetailsRepository orderdetailsrepository) {
		this.orderdetailsrepository = orderdetailsrepository;
	}

	@Override
	public <S extends Orderdetails> S save(S entity) {
		return orderdetailsrepository.save(entity);
	}

	@Override
	public <S extends Orderdetails> List<S> saveAll(Iterable<S> entities) {
		return orderdetailsrepository.saveAll(entities);
	}

	@Override
	public <S extends Orderdetails> Optional<S> findOne(Example<S> example) {
		return orderdetailsrepository.findOne(example);
	}

	@Override
	public List<Orderdetails> findAll(Sort sort) {
		return orderdetailsrepository.findAll(sort);
	}

	@Override
	public void flush() {
		orderdetailsrepository.flush();
	}

	@Override
	public Page<Orderdetails> findAll(Pageable pageable) {
		return orderdetailsrepository.findAll(pageable);
	}

	@Override
	public <S extends Orderdetails> S saveAndFlush(S entity) {
		return orderdetailsrepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Orderdetails> List<S> saveAllAndFlush(Iterable<S> entities) {
		return orderdetailsrepository.saveAllAndFlush(entities);
	}

	@Override
	public List<Orderdetails> findAll() {
		return orderdetailsrepository.findAll();
	}

	@Override
	public List<Orderdetails> findAllById(Iterable<Integer> ids) {
		return orderdetailsrepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<Orderdetails> entities) {
		orderdetailsrepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Orderdetails> Page<S> findAll(Example<S> example, Pageable pageable) {
		return orderdetailsrepository.findAll(example, pageable);
	}

	@Override
	public Optional<Orderdetails> findById(Integer id) {
		return orderdetailsrepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<Orderdetails> entities) {
		orderdetailsrepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return orderdetailsrepository.existsById(id);
	}

	@Override
	public <S extends Orderdetails> long count(Example<S> example) {
		return orderdetailsrepository.count(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		orderdetailsrepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Orderdetails> boolean exists(Example<S> example) {
		return orderdetailsrepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		orderdetailsrepository.deleteAllInBatch();
	}

	@Override
	public Orderdetails getOne(Integer id) {
		return orderdetailsrepository.getOne(id);
	}

	@Override
	public <S extends Orderdetails, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return orderdetailsrepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return orderdetailsrepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		orderdetailsrepository.deleteById(id);
	}

	@Override
	public Orderdetails getById(Integer id) {
		return orderdetailsrepository.getById(id);
	}

	@Override
	public void delete(Orderdetails entity) {
		orderdetailsrepository.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		orderdetailsrepository.deleteAllById(ids);
	}

	@Override
	public Orderdetails getReferenceById(Integer id) {
		return orderdetailsrepository.getReferenceById(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Orderdetails> entities) {
		orderdetailsrepository.deleteAll(entities);
	}

	@Override
	public <S extends Orderdetails> List<S> findAll(Example<S> example) {
		return orderdetailsrepository.findAll(example);
	}

	@Override
	public <S extends Orderdetails> List<S> findAll(Example<S> example, Sort sort) {
		return orderdetailsrepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		orderdetailsrepository.deleteAll();
	}
	
	
	
}
