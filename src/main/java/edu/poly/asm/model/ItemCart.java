package edu.poly.asm.model;

import java.sql.Date;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemCart {
	@Id
	private Integer productid;
	private String productname;
	private Float price;
	private Integer quantity;
	private String image;
}
