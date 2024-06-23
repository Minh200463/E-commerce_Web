package edu.poly.asm.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Orders")
public class Orders implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderid;
	private Date orderdate;
	private float totalamount;
	private int status;
	
	@ManyToOne
	@JoinColumn(name = "userid")
	Users userid;
	
	@OneToMany(mappedBy = "orderid", fetch = FetchType.EAGER)
	@JsonIgnore
	List<Orderdetails> orderdetails;
}
	