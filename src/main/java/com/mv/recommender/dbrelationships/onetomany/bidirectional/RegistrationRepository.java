package com.mv.recommender.dbrelationships.onetomany.bidirectional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
}
