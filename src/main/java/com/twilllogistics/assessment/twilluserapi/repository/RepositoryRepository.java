package com.twilllogistics.assessment.twilluserapi.repository;

import com.twilllogistics.assessment.twilluserapi.pojo.RepositoryCore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

/**
 * Created by turgay on 31/07/17.
 * <p>
 * Although its funny name, this class is the JPA repository for the Repository objects.
 */
@Repository
public interface RepositoryRepository extends JpaRepository<RepositoryCore, Long> {

    Stream<RepositoryCore> findRepositoriesByOwner_Login(String login);
}
