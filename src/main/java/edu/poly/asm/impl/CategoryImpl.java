package edu.poly.asm.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.asm.model.Categories;
import edu.poly.asm.repository.CategoryRepository;
import edu.poly.asm.service.CategoryService;

@Service
public class CategoryImpl implements CategoryService{
	CategoryRepository categoryrepository;

	public CategoryImpl(CategoryRepository categoryrepository) {
		this.categoryrepository = categoryrepository;
	}

	@Override
	public <S extends Categories> S save(S entity) {
		return categoryrepository.save(entity);
	}

	@Override
	public <S extends Categories> List<S> saveAll(Iterable<S> entities) {
		return categoryrepository.saveAll(entities);
	}

	@Override
	public List<Categories> findAll(Sort sort) {
		return categoryrepository.findAll(sort);
	}

	@Override
	public Page<Categories> findAll(Pageable pageable) {
		return categoryrepository.findAll(pageable);
	}

	@Override
	public <S extends Categories> S saveAndFlush(S entity) {
		return categoryrepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Categories> List<S> saveAllAndFlush(Iterable<S> entities) {
		return categoryrepository.saveAllAndFlush(entities);
	}

	@Override
	public List<Categories> findAll() {
		return categoryrepository.findAll();
	}

	@Override
	public List<Categories> findAllById(Iterable<Integer> ids) {
		return categoryrepository.findAllById(ids);
	}

	@Override
	public Optional<Categories> findById(Integer id) {
		return categoryrepository.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<Categories> entities) {
		categoryrepository.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return categoryrepository.existsById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		categoryrepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Categories> boolean exists(Example<S> example) {
		return categoryrepository.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		categoryrepository.deleteAllInBatch();
	}

	@Override
	public long count() {
		return categoryrepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		categoryrepository.deleteById(id);
	}

	@Override
	public Categories getById(Integer id) {
		return categoryrepository.getById(id);
	}

	@Override
	public void delete(Categories entity) {
		categoryrepository.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		categoryrepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Categories> entities) {
		categoryrepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		categoryrepository.deleteAll();
	}
	
	
}
