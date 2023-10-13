package com.digitalsanctuary.spring.user.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Zahlungsinformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String IBAN;
    String BIC;
    String Kontoinhaber;
}
