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

    /**
     * Queries database for user data by her login name
     * @param login login name of user
     * @return The core user object, which is UserProfile
     */
    UserProfile findByLogin(String login);

    /**
     * Queries database for user data by her login name, but different from findByLogin method,
     * as this one returns UserProfileSummary object, which included a small set set user attributes.
     * @param login user login name
     * @return UserProfileSummary object
     */
    @Query("select new com.twilllogistics.assessment.twilluserapi.pojo.UserProfileSummary(up.login, up.id, up.avatar_url, up.gravatar_id, up.type, up.site_admin) " +
            "from UserProfile up where up.login= ?1")
    UserProfileSummary findSummaryByLogin(String login);

    /**
     * Queries database for user ID's by user login names
     * @param username User login name
     * @return User id (primary key
     */
    @Query("select up.id from UserProfile up where up.login= ?1")
    Long findIdByLogin(String username);

    /**
     * Queries for number of public repositories by user id
     * @param id user ID (primary key
     * @return number of public repositories
     */
    @Query("select count(*) from RepositoryCore r where r.id = ?1 and r.isPrivate=false")
    Long getNumberOfPublicRepositoriesByUserId(Long id);
}
