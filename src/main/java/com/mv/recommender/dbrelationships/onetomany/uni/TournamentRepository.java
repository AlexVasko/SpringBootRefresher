package com.mv.recommender.dbrelationships.onetomany.uni;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament,Integer> {
}
