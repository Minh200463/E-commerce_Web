package edu.poly.asm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.poly.asm.model.Orderdetails;
import edu.poly.asm.model.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
//	@Query("SELECT o FROM Orders o " +
//	           "JOIN o.userid u " +
//	           "JOIN o.orderdetails od " +
//	           "JOIN od.productid p " +
//	           "WHERE u.username LIKE %:keyword% " +
//	           "OR p.productname LIKE %:keyword%")
//	    Page<Orders> findOrdersByKeyword(@Param("keyword") String keyword,  Pageable pageable);

	 
}
