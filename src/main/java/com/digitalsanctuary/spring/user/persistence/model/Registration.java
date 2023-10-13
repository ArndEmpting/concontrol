package com.digitalsanctuary.spring.user.persistence.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Registration {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String vorname; // first name
    private String altersklasse; // age category
    private String strasseNummerZusatz; // street, number & additional address info
    @Pattern(regexp = "\\d*", message = "PLZ must be a number.")
    private String plz; // postal code
    private String ort; //
    @Email
    private String email;
    private String telefon;
    private String magiraName;
    private String magiraVolk;
    private String anreise; // arrival type
    private String kfzKennzeichen; // vehicle id plate
    private boolean agb; // a checkbox indication
    private boolean datenschutzerklaerung; // a checkbox indication
    private LocalDateTime anmeldedatumUhrzeit; // registration DateTime
    private String status;
    private String zusammenMit; // a text field
    private String besonderheiten; // a text field
    private boolean laermempfindlich; // a checkbox indication
    private boolean raucher; // a checkbox indication
    private boolean schnarcher; // a checkbox indication
    private boolean vegetarier; // a checkbox indication
    private String kommentar; // comments
    private double bezahlt; // paid - money value

    // getters and setters ....
}