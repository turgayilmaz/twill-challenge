package com.twilllogistics.assessment.twilluserapi.repository;

import com.twilllogistics.assessment.twilluserapi.pojo.UserProfile;
import com.twilllogistics.assessment.twilluserapi.pojo.UserProfileSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by turgay on 31/07/17.
 *
 * This class is the JPA repository for the User objects.
 */
@Repository
public interface UserRepository extends JpaRepository<UserProfile, Long> {

    UserProfile findByLogin(String login);

    @Query("select new com.twilllogistics.assessment.twilluserapi.pojo.UserProfileSummary(up.login, up.id, up.avatar_url, up.gravatar_id, up.type, up.site_admin) " +
            "from UserProfile up where up.login= ?1")
    UserProfileSummary findSummaryByLogin(String login);

    @Query("select up.id from UserProfile up where up.login= ?1")
    Long findIdByLogin(String username);

    @Query("select count(*) from RepositoryCore r where r.id = ?1 and r.isPrivate=false")
    Long getUserStatisticsById(Long id);
}
