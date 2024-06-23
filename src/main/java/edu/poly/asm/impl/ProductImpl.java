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

import edu.poly.asm.model.Products;
import edu.poly.asm.repository.ProductRepository;
import edu.poly.asm.service.CategoryService;
import edu.poly.asm.service.ProductService;

@Service
public class ProductImpl implements ProductService{
	ProductRepository productrepository;

	public ProductImpl(ProductRepository productrepository) {
		this.productrepository = productrepository;
	}

	@Override
	public <S extends Products> S save(S entity) {
		return productrepository.save(entity);
	}

	@Override
	public <S extends Products> List<S> saveAll(Iterable<S> entities) {
		return productrepository.saveAll(entities);
	}

	@Override
	public <S extends Products> Optional<S> findOne(Example<S> example) {
		return productrepository.findOne(example);
	}

	@Override
	public List<Products> findAll(Sort sort) {
		return productrepository.findAll(sort);
	}

	@Override
	public void flush() {
		productrepository.flush();
	}

	@Override
	public Page<Products> findAll(Pageable pageable) {
		return productrepository.findAll(pageable);
	}

	@Override
	public <S extends Products> S saveAndFlush(S entity) {
		return productrepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Products> List<S> saveAllAndFlush(Iterable<S> entities) {
		return productrepository.saveAllAndFlush(entities);
	}

	@Override
	public List<Products> findAll() {
		return productrepository.findAll();
	}

	@Override
	public List<Products> findAllById(Iterable<Integer> ids) {
		return productrepository.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<Products> entities) {
		productrepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Products> Page<S> findAll(Example<S> example, Pageable pageable) {
		return productrepository.findAll(example, pageable);
	}

	@Override
	public Optional<Products> findById(Integer id) {
		return productrepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<Products> entities) {
		productrepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return productrepository.existsById(id);
	}

	@Override
	public <S extends Products> long count(Example<S> example) {
		return productrepository.count(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		productrepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Products> boolean exists(Example<S> example) {
		return productrepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		productrepository.deleteAllInBatch();
	}

	@Override
	public Products getOne(Integer id) {
		return productrepository.getOne(id);
	}

	@Override
	public <S extends Products, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return productrepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return productrepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		productrepository.deleteById(id);
	}

	@Override
	public Products getById(Integer id) {
		return productrepository.getById(id);
	}

	@Override
	public void delete(Products entity) {
		productrepository.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		productrepository.deleteAllById(ids);
	}

	@Override
	public Products getReferenceById(Integer id) {
		return productrepository.getReferenceById(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Products> entities) {
		productrepository.deleteAll(entities);
	}

	@Override
	public <S extends Products> List<S> findAll(Example<S> example) {
		return productrepository.findAll(example);
	}

	@Override
	public <S extends Products> List<S> findAll(Example<S> example, Sort sort) {
		return productrepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		productrepository.deleteAll();
	}

	
	
}
