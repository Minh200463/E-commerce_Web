package edu.poly.asm.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class Users implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userid;
	@NotEmpty(message = "{NotEmpty.user.username}")
	private String username;
	@NotEmpty(message = "{NotEmpty.user.password}")
	private String password;
	@NotEmpty(message = "{NotEmpty.user.fullname}")
	private String fullname;
	@NotEmpty(message = "{NotEmpty.user.email}")
	private String email;
	@NotEmpty(message = "{NotNull.user.phone}")
	private String phone;
	private boolean admin;
	private String address;
	private boolean gender;

	@OneToMany(mappedBy = "userid")
	@JsonBackReference
	List<Orders> orders;
}
