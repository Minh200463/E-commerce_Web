package edu.poly.asm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.poly.asm.model.Categories;
import edu.poly.asm.model.Products;

public interface ProductRepository extends JpaRepository<Products, Integer> {
	
	@Query("SELECT p FROM Products p WHERE p.discount IS NOT NULL AND p.discount > 0 ORDER BY p.discount DESC")
	List<Products> findTop8DiscountedProducts(Pageable pageable);
	
//	find product with like productname
	Page<Products> findByProductnameLike(String productname, Pageable page);
	
	//find product with categoryid
	Page<Products> findByCategories_Categoryid(Integer categoryid, Pageable page);
	
	//Find all product with categoryid = 1 (categoryname = 'Latop')
//	List<Products> findByCategoryid
	List<Products> findByStatus(boolean status);
}
