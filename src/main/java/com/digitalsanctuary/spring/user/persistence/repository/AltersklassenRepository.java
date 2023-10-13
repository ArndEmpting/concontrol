package com.digitalsanctuary.spring.user.persistence.repository;

import com.digitalsanctuary.spring.user.persistence.model.Altersklasse;
import com.digitalsanctuary.spring.user.persistence.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AltersklassenRepository  extends JpaRepository<Altersklasse, Long> {
    Privilege findByName(String name);
}
