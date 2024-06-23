package edu.poly.asm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.poly.asm.model.Products;
import edu.poly.asm.model.Users;

public interface UserReponsitory extends JpaRepository<Users, Integer> {

	Page<Users> findByUsernameLike(String username, Pageable page);
	
	Users findByUsername(String username);
}