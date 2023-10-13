package com.digitalsanctuary.spring.user.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.ToString;

import java.util.Collection;

/**
 * The Privilege Entity. Part of the basic User ->> Role ->> Privilege structure.
 */
@Data
@Entity
public class Altersklasse {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/** The name. */
	private String name;


}
