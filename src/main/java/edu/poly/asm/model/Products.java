package edu.poly.asm.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Products")
public class Products implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productid;
	@NotEmpty(message = "{NotEmpty.product.name}")
	private String productname;
	@NotEmpty(message = "{NotEmpty.product.description}")
	private String description;
	@NotNull(message = "{NotNull.product.price}")
	private Float price;
	
	private Float discount;
	@NotNull(message = "{NotNull.product.quantity}")
	private Integer quantity;
	private String image;
	private boolean status;
	private Date createdate;
	
	@OneToMany(mappedBy = "productid")
	List<Orderdetails> orderdetail;
	
	@ManyToOne
	@JoinColumn(name = "categoryid")
	@NotNull(message = "{NotNull.product.categoryid}")
	Categories categories;
}
