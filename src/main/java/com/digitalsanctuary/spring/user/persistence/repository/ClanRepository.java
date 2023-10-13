package com.digitalsanctuary.spring.user.persistence.repository;

import com.digitalsanctuary.spring.user.persistence.model.Altersklasse;
import com.digitalsanctuary.spring.user.persistence.model.Clan;
import com.digitalsanctuary.spring.user.persistence.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClanRepository extends JpaRepository<Clan, Long> {
    Privilege findByName(String name);
}
