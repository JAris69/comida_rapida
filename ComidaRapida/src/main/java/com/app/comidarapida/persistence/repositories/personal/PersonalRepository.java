package com.app.comidarapida.persistence.repositories.personal;

import com.app.comidarapida.persistence.entities.personal.PersonalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalRepository extends JpaRepository<PersonalEntity, Long> {


}
