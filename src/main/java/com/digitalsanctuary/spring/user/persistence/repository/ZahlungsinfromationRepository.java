package com.digitalsanctuary.spring.user.persistence.repository;

import com.digitalsanctuary.spring.user.persistence.model.Clan;
import com.digitalsanctuary.spring.user.persistence.model.Privilege;
import com.digitalsanctuary.spring.user.persistence.model.Zahlungsinformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZahlungsinfromationRepository extends JpaRepository<Zahlungsinformation, Long> {
    Privilege findByName(String name);
}
