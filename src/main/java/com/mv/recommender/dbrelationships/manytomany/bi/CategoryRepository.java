package com.mv.recommender.dbrelationships.manytomany.bi;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
