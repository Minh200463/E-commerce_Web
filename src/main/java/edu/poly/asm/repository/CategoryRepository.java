package edu.poly.asm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.asm.model.Categories;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Integer> {
	
}
