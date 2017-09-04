package com.twilllogistics.assessment.twilluserapi.service;

import com.twilllogistics.assessment.twilluserapi.data.User;
import com.twilllogistics.assessment.twilluserapi.exception.InvalidArgumentException;
import com.twilllogistics.assessment.twilluserapi.pojo.UserApiUrls;
import com.twilllogistics.assessment.twilluserapi.pojo.UserProfile;
import com.twilllogistics.assessment.twilluserapi.pojo.UserStatistics;
import com.twilllogistics.assessment.twilluserapi.repository.RepositoryRepository;
import com.twilllogistics.assessment.twilluserapi.repository.StatisticsRepository;
import com.twilllogistics.assessment.twilluserapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.twilllogistics.assessment.twilluserapi.service.ApiUrlConstants.*;

/**
 * Created by turgay on 31/08/17.
 * <p>
 * UserService provides services for retrieving and creating users.
 * <p>
 * getUserByUserName service for User data returns User objects, while createUser service
 * expects a UserProfile object as parameter, since a UserProfile object is actually enough
 * to create a UserProfile object. In DB, only the UserProfile is stored.
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RepositoryRepository repositoryRepository;

    @Autowired
    StatisticsRepository statisticsRepository;

    @Value("${api.deployment.url}")
    String apiDeploymentUrl;

    @Value("${base.url}")
    String baseUrl;

    public User getUserByUserName(String username) {
        UserProfile userProfile = userRepository.findByLogin(username);
        if (userProfile != null) {
            return getUser(userProfile);
        }
        return null;

    }

    User getUser(UserProfile userProfile) {
        UserApiUrls userApiUrls = createUserApiUrlWithLoginName(userProfile.getLogin());
        UserStatistics userStatistics = statisticsRepository.getUserStatisticsById(userProfile.getId());
        return new User(userProfile, userApiUrls, userStatistics);
    }

    UserApiUrls createUserApiUrlWithLoginName(String loginName) {
        UserApiUrls urls = new UserApiUrls();
        urls.setUrl(apiDeploymentUrl + USERS + SLASH + loginName);
        urls.setHtml_url(HTTPS + baseUrl + SLASH + loginName);
        urls.setFollowers_url(urls.getUrl() + FOLLOWERS);
        urls.setFollowing_url(urls.getUrl() + FOLLOWING);
        urls.setGists_url(urls.getUrl() + GISTS);
        urls.setStarred_url(urls.getUrl() + STARRED);
        urls.setSubscriptions_url(urls.getUrl() + SUBSCRIPTIONS);
        urls.setOrganizations_url(urls.getUrl() + ORGS);
        urls.setRepos_url(urls.getUrl() + REPOS);
        urls.setEvents_url(urls.getUrl() + EVENTS_PRIVACY);
        urls.setReceived_events_url(urls.getUrl() + RECEIVED_EVENTS);
        return urls;
    }

    public void createUser(UserProfile userProfile) throws InvalidArgumentException {
        String errors = checkDataValidity(userProfile);
        if (errors != null) {
            throw new InvalidArgumentException(errors);
        }
        userRepository.save(userProfile);
    }

    String checkDataValidity(UserProfile userProfile) {
        if (userProfile == null) {
            return "UserProfile should not be null";
        } else if (userProfile.getLogin() == null) {
            return "UserProfile.login should not be null";
        } else {
            return null;
        }
    }
}
