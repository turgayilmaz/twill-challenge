package com.twilllogistics.assessment.twilluserapi.repository;

import com.twilllogistics.assessment.twilluserapi.pojo.RepositoryStatistics;
import com.twilllogistics.assessment.twilluserapi.pojo.UserStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Random;

/**
 * Created by turgay on 31/08/17.
 * <p>
 * This class is the repository for the Statistics objects.
 */
@Repository
public class StatisticsRepository {

    @Autowired
    UserRepository userRepository;

    /**
     * This is a mockup implementation for returning statistics related to the repository.
     *
     * @param id ID for the repository
     * @return RepositoryStatistics object related with the repository
     */
    public RepositoryStatistics getRepositoryStatisticsById(Long id) {
        Random rnd = new Random(id);
        RepositoryStatistics statistics = new RepositoryStatistics();
        statistics.setForks(rnd.nextInt());
        statistics.setForks_count(rnd.nextInt());
        statistics.setHas_downloads(rnd.nextBoolean());
        statistics.setHas_issues(rnd.nextBoolean());
        statistics.setHas_pages(rnd.nextBoolean());
        statistics.setHas_projects(rnd.nextBoolean());
        statistics.setHas_wiki(rnd.nextBoolean());
        statistics.setOpen_issues(rnd.nextInt());
        statistics.setOpen_issues_count(rnd.nextInt());
        statistics.setSize(rnd.nextLong());
        statistics.setStargazers_count(rnd.nextInt());
        statistics.setWatchers(rnd.nextInt());
        statistics.setWatchers_count(rnd.nextInt());

        return statistics;
    }

    /**
     * This method contains half real data, half mock-up.
     * Since we have repositories in out database, we get the number of public repos
     * from the database, but other fields are mocked up.
     *
     * @param id ID for the user
     * @return UserStatistics object related with the user
     */
    public UserStatistics getUserStatisticsById(Long id) {
        Random rnd = new Random(id);
        UserStatistics statistics = new UserStatistics();
        statistics.setFollowers(rnd.nextInt());
        statistics.setFollowing(rnd.nextInt());
        statistics.setPublic_gists(rnd.nextInt());
        statistics.setPublic_repos((int) userRepository.getUserStatisticsById(id).longValue());

        return statistics;
    }
}
