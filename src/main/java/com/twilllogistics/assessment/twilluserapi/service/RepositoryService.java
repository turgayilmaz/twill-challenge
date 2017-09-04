package com.twilllogistics.assessment.twilluserapi.service;

import com.twilllogistics.assessment.twilluserapi.data.Repository;
import com.twilllogistics.assessment.twilluserapi.data.UserSummary;
import com.twilllogistics.assessment.twilluserapi.exception.InvalidArgumentException;
import com.twilllogistics.assessment.twilluserapi.pojo.*;
import com.twilllogistics.assessment.twilluserapi.repository.RepositoryRepository;
import com.twilllogistics.assessment.twilluserapi.repository.StatisticsRepository;
import com.twilllogistics.assessment.twilluserapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.twilllogistics.assessment.twilluserapi.service.ApiUrlConstants.*;

/**
 * Created by turgay on 31/08/17.
 * <p>
 * RepositoryService provides services for retrieving and creating repositories.
 *
 * getRepositoriesOfUser service returns Repository objects, while createRepositoryForUser service
 * expects a RepositoryCore object as parameter, since a RepositoryCore object is actually enough
 * to create a Repository object. In DB, only the RepositoryCore is stored.
 */
@Service
public class RepositoryService {

    @Autowired
    RepositoryRepository repositoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StatisticsRepository statisticsRepository;

    @Autowired
    UserService userService;

    @Value("${api.deployment.url}")
    String apiDeploymentUrl;

    @Value("${base.url}")
    String baseUrl;

    @Transactional
    public List<Repository> getRepositoriesOfUser(String username) {
        UserSummary owner = getOwner(username);
        if (owner != null) {
            Stream<RepositoryCore> repositoryCoreStream =
                    repositoryRepository.findRepositoriesByOwner_Login(username).parallel();
            return repositoryCoreStream
                    .map(repositoryCore -> constructNewRepository(username, owner, repositoryCore))
                    .collect(Collectors.toList());
        } else {
            return null;
        }

    }

    UserSummary getOwner(String username) {
        UserProfileSummary userProfileSummary = userRepository.findSummaryByLogin(username);
        if (userProfileSummary != null) {
            return getUserSummary(username, userProfileSummary);
        }
        return null;
    }

    UserSummary getUserSummary(String username, UserProfileSummary userProfileSummary) {
        UserApiUrls userApiUrls = userService.createUserApiUrlWithLoginName(username);
        UserSummary owner = new UserSummary();
        owner.setUserProfileSummary(userProfileSummary);
        owner.setUserApiUrls(userApiUrls);
        return owner;
    }

    Repository constructNewRepository(String username, UserSummary owner, RepositoryCore repositoryCore) {
        Repository repository = new Repository();
        repository.setRepositoryCore(repositoryCore);
        repository.setOwner(owner);
        RepositoryApiUrls repositoryApiUrls = createRepositoryApiUrls(username, repositoryCore.getName());
        repository.setRepositoryApiUrls(repositoryApiUrls);
        RepositoryVcsUrls repositoryVcsUrls = createRepositoryVcsUrls(repositoryApiUrls.getFull_name());
        repository.setRepositoryVcsUrls(repositoryVcsUrls);
        repository.setRepositoryStatistics(statisticsRepository.getRepositoryStatisticsById(repository.getRepositoryCore().getId()));
        return repository;
    }

    RepositoryApiUrls createRepositoryApiUrls(String username, String repoName) {
        RepositoryApiUrls urls = new RepositoryApiUrls();
        urls.setFull_name(username + SLASH + repoName);
        urls.setHtml_url(HTTPS + baseUrl + SLASH + urls.getFull_name());
        urls.setUrl(apiDeploymentUrl + REPOS + SLASH + urls.getFull_name());
        urls.setForks_url(urls.getUrl() + FORKS);
        urls.setKeys_url(urls.getUrl() + KEYS);
        urls.setCollaborators_url(urls.getUrl() + COLLABORATORS);
        urls.setTeams_url(urls.getUrl() + TEAMS);
        urls.setHooks_url(urls.getUrl() + HOOKS);
        urls.setIssue_events_url(urls.getUrl() + ISSUES_EVENTS);
        urls.setEvents_url(urls.getUrl() + EVENTS);
        urls.setAssignees_url(urls.getUrl() + ASSIGNEES);
        urls.setBranches_url(urls.getUrl() + BRANCHES);
        urls.setTags_url(urls.getUrl() + TAGS);
        urls.setBlobs_url(urls.getUrl() + GIT_BLOBS);
        urls.setGit_tags_url(urls.getUrl() + GIT_TAGS);
        urls.setGit_refs_url(urls.getUrl() + GIT_REFS);
        urls.setTrees_url(urls.getUrl() + GIT_TREES);
        urls.setStatuses_url(urls.getUrl() + STATUSES);
        urls.setLanguages_url(urls.getUrl() + LANGUAGES);
        urls.setStargazers_url(urls.getUrl() + STARGAZERS);
        urls.setContributors_url(urls.getUrl() + CONTRIBUTORS);
        urls.setSubscribers_url(urls.getUrl() + SUBSCRIBERS);
        urls.setSubscription_url(urls.getUrl() + SUBSCRIPTION);
        urls.setCommits_url(urls.getUrl() + COMMITS);
        urls.setGit_commits_url(urls.getUrl() + GIT_COMMITS);
        urls.setComments_url(urls.getUrl() + COMMENTS);
        urls.setIssue_comment_url(urls.getUrl() + ISSUES_COMMENTS);
        urls.setContents_url(urls.getUrl() + CONTENTS);
        urls.setCompare_url(urls.getUrl() + COMPARE);
        urls.setMerges_url(urls.getUrl() + MERGES);
        urls.setArchive_url(urls.getUrl() + ARCHIVE_FORMAT_REF);
        urls.setDownloads_url(urls.getUrl() + DOWNLOADS);
        urls.setIssues_url(urls.getUrl() + ISSUES);
        urls.setPulls_url(urls.getUrl() + PULLS);
        urls.setMilestones_url(urls.getUrl() + MILESTONES);
        urls.setNotifications_url(urls.getUrl() + NOTIFICATIONS);
        urls.setLabels_url(urls.getUrl() + LABELS);
        urls.setReleases_url(urls.getUrl() + RELEASES);
        urls.setDeployments_url(urls.getUrl() + DEPLOYMENTS);
        return urls;
    }

    RepositoryVcsUrls createRepositoryVcsUrls(String fullName) {
        RepositoryVcsUrls urls = new RepositoryVcsUrls();
        urls.setGit_url(GIT_PROTOCOL + baseUrl + SLASH + fullName + GIT_EXTENSION);
        urls.setSsh_url(GIT_AT + baseUrl + SEMICOLON + fullName + GIT_EXTENSION);
        urls.setClone_url(HTTPS + baseUrl + SLASH + fullName + GIT_EXTENSION);
        urls.setSvn_url(HTTPS + baseUrl + SLASH + fullName);
        return urls;
    }

    public void createRepositoryForUser(String username, RepositoryCore repository) throws InvalidArgumentException {
        Long userId = userRepository.findIdByLogin(username);
        if (userId == null) {
            throw new InvalidArgumentException("User Not Found");
        }
        String errors = checkDataValidity(repository);
        if (errors != null) {
            throw new InvalidArgumentException(errors);
        }
        UserProfile owner = new UserProfile();
        owner.setId(userId);
        repository.setOwner(owner);
        repositoryRepository.save(repository);
    }

    String checkDataValidity(RepositoryCore repositoryCore) {
        if (repositoryCore == null) {
            return "Repository data should not be null";
        } else if (repositoryCore.getName() == null) {
                return "Repository.name should not be null";
        } else {
                return null;
        }
    }
}
