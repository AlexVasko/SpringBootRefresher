package com.mv.recommender.dbrelationships.manytomany.bi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TournamentRepository extends JpaRepository<Tournament,Integer> {
}
