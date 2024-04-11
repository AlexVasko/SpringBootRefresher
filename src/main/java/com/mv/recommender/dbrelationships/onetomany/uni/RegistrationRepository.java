package com.mv.recommender.dbrelationships.onetomany.uni;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
}
