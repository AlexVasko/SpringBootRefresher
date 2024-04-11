package com.mv.recommender.dbrelationships.onetomany.bidirectional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament,Integer> {
}
