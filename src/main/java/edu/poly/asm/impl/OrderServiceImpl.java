package edu.poly.asm.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.poly.asm.model.Orderdetails;
import edu.poly.asm.model.Orders;
import edu.poly.asm.repository.OrderRepository;
import edu.poly.asm.repository.OrderdetailsRepository;
import edu.poly.asm.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderdetailsRepository orderdetailsrepository;

	OrderRepository orderepository;

	public OrderServiceImpl(OrderRepository orderepository) {
		this.orderepository = orderepository;
	}

	@Override
	public <S extends Orders> S save(S entity) {
		return orderepository.save(entity);
	}

	@Override
	public <S extends Orders> List<S> saveAll(Iterable<S> entities) {
		return orderepository.saveAll(entities);
	}

	@Override
	public <S extends Orders> Optional<S> findOne(Example<S> example) {
		return orderepository.findOne(example);
	}

	@Override
	public List<Orders> findAll(Sort sort) {
		return orderepository.findAll(sort);
	}

	@Override
	public void flush() {
		orderepository.flush();
	}

	@Override
	public Page<Orders> findAll(Pageable pageable) {
		return orderepository.findAll(pageable);
	}

	@Override
	public <S extends Orders> S saveAndFlush(S entity) {
		return orderepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Orders> List<S> saveAllAndFlush(Iterable<S> entities) {
		return orderepository.saveAllAndFlush(entities);
	}

	@Override
	public List<Orders> findAll() {
		return orderepository.findAll();
	}

	@Override
	public List<Orders> findAllById(Iterable<Integer> ids) {
		return orderepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<Orders> entities) {
		orderepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Orders> Page<S> findAll(Example<S> example, Pageable pageable) {
		return orderepository.findAll(example, pageable);
	}

	@Override
	public Optional<Orders> findById(Integer id) {
		return orderepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<Orders> entities) {
		orderepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return orderepository.existsById(id);
	}

	@Override
	public <S extends Orders> long count(Example<S> example) {
		return orderepository.count(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		orderepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Orders> boolean exists(Example<S> example) {
		return orderepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		orderepository.deleteAllInBatch();
	}

	@Override
	public Orders getOne(Integer id) {
		return orderepository.getOne(id);
	}

	@Override
	public <S extends Orders, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return orderepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return orderepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		orderepository.deleteById(id);
	}

	@Override
	public Orders getById(Integer id) {
		return orderepository.getById(id);
	}

	@Override
	public void delete(Orders entity) {
		orderepository.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		orderepository.deleteAllById(ids);
	}

	@Override
	public Orders getReferenceById(Integer id) {
		return orderepository.getReferenceById(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Orders> entities) {
		orderepository.deleteAll(entities);
	}

	@Override
	public <S extends Orders> List<S> findAll(Example<S> example) {
		return orderepository.findAll(example);
	}

	@Override
	public <S extends Orders> List<S> findAll(Example<S> example, Sort sort) {
		return orderepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		orderepository.deleteAll();
	}
	
}
