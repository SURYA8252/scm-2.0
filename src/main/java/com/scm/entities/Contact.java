package com.scm.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_contact")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Contact {

	@Id
	private String id;
	private String name;
	private String email;
	private String phoneNumber;
	private String address;
	private String picture;
	private String description;
	private boolean favorite = false;
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "contact", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<SocialLink> links = new ArrayList<>();
}
