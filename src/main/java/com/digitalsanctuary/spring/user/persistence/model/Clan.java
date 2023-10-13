package com.digitalsanctuary.spring.user.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * The Privilege Entity. Part of the basic User ->> Role ->> Privilege structure.
 */
@Data
@Entity
public class Clan {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/** The name. */
	private String name;

	
}
