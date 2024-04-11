package com.mv.recommender.lesson18;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;


@SpringBootApplication
public class TennisPlayerApplication implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PlayerSpringDataRepository repo;

    public static void main(String[] args) {
        SpringApplication.run(TennisPlayerApplication.class, args);
    }
    @Override
    public void run(String... args)  {
        logger.info("\n\n>> Inserting Player: {}\n", repo.save(
                new Player("Djokovic", "Serbia", Date.valueOf("1987-05-22"), 81)));

        logger.info("\n\n>> Inserting Player: {}\n", repo.save(
                new Player("Monfils", "France", Date.valueOf("1986-09-01"), 10)));

        logger.info("\n\n>> Player with id 2: {}\n", repo.findById(2));

        logger.info("\n\n>> Inserting Player: {}\n", repo.save(
                new Player("Thiem", "Austria",
                        new Date(System.currentTimeMillis()), 17)));
        //update player
        logger.info("\n\n>> Updating Player with Id 3: {}\n", repo.save(
                new Player(3, "Thiem", "Austria", Date.valueOf("1993-09-03"), 17)));

        logger.info("\n\n>> Player with id 3: {}\n", repo.findById(3));

        logger.info("All Players Data: {}", repo.findAll());

        logger.info("French Players: {}", repo.findByNationality("France"));
        repo.deleteById(2);
    }
}
