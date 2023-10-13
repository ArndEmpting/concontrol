package com.digitalsanctuary.spring.user.persistence.repository;

import com.digitalsanctuary.spring.user.persistence.model.Clan;
import com.digitalsanctuary.spring.user.persistence.model.Event;
import com.digitalsanctuary.spring.user.persistence.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
    Privilege findByName(String name);
}
