package com.example.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Etudiant implements Serializable {

	@Id
	@GeneratedValue
	private Long id;
	@Column (name="NOM",length=30)
	@org.hibernate.validator.constraints.NotEmpty
	@Size (min=5,max=30)
	private String nom;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date;
	@org.hibernate.validator.constraints.NotEmpty
	@Email
	private String email;
	private String photo;
	
	public Etudiant() {
		super();
	}
	
	public Etudiant(String nom, Date date, String email, String photo) {
		super();
		
		this.nom = nom;
		this.date = date;
		this.email = email;
		this.photo = photo;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	



	
	
	
}
