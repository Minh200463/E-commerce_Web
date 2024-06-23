package edu.poly.asm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.poly.asm.model.Orderdetails;
import edu.poly.asm.model.Orders;
import edu.poly.asm.model.Products;

public interface OrderdetailsRepository extends JpaRepository<Orderdetails, Integer> {
	@Query("SELECT od FROM Orderdetails od " + "JOIN od.orderid o " + "JOIN o.userid u " + "JOIN od.productid p "
			+ "WHERE u.username LIKE %:keyword% " + "OR p.productname LIKE %:keyword%")
	Page<Orderdetails> findOrderdetailsByKeyword(@Param("keyword") String keyword, Pageable pageable);

	List<Orderdetails> findAllByOrderid_Orderid(int orderid);

	// Lấy ra doanh thu(revenue) của các tháng trong năm
	@Query("SELECT MONTH(o.orderdate) AS month, SUM(od.quantity * od.unitprice) AS revenue "
			+ "FROM Orders o JOIN o.orderdetails od " + "WHERE YEAR(o.orderdate) = :year "
			+ "GROUP BY MONTH(o.orderdate) " + "ORDER BY MONTH(o.orderdate)")
	List<Object[]> findRevenueByMonthAndYear(@Param("year") Integer year);

	// Top 10 product bán chạy
	@Query(value = "SELECT TOP 10 p.productid, p.productname, p.price, c.categoryname " + "FROM Products p "
			+ "JOIN Orderdetails od ON p.productid = od.productid " + "JOIN Orders o ON od.orderid = o.orderid "
			+ "JOIN Categories c ON p.categoryid = c.categoryid " + "WHERE o.status = 1 "
			+ "GROUP BY p.productid, p.productname, p.price, c.categoryname "
			+ "ORDER BY SUM(od.quantity) DESC", nativeQuery = true)
	List<Object[]> findTopSellingProducts();

	// Total order day now
	@Query(value = "SELECT o.orderid, u.fullname, o.orderdate, o.totalamount " + "FROM Orders o "
			+ "JOIN Users u ON o.userid = u.userid "
			+ "WHERE CAST(o.orderdate AS DATE) = CAST(GETDATE() AS DATE)", nativeQuery = true)
	List<Object[]> findTodayOrders();
}
