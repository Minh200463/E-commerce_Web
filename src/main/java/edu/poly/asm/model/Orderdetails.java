package edu.poly.asm.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Orderdetails")
public class Orderdetails implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderdetailid;
	private int quantity;
	private float unitprice;
	
	@ManyToOne
	@JoinColumn(name = "orderid")
	Orders orderid;
	
	@ManyToOne
	@JoinColumn(name = "productid")
	Products productid;
}
