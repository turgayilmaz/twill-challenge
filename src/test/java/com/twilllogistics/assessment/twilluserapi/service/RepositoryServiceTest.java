package com.twilllogistics.assessment.twilluserapi.service;

import com.twilllogistics.assessment.twilluserapi.data.Repository;
import com.twilllogistics.assessment.twilluserapi.data.UserSummary;
import com.twilllogistics.assessment.twilluserapi.data.UserType;
import com.twilllogistics.assessment.twilluserapi.exception.InvalidArgumentException;
import com.twilllogistics.assessment.twilluserapi.pojo.*;
import com.twilllogistics.assessment.twilluserapi.repository.RepositoryRepository;
import com.twilllogistics.assessment.twilluserapi.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.twilllogistics.assessment.twilluserapi.service.TestHelper.invokeMethod;
import static org.junit.Assert.*;

/**
 * Created by turgay on 31/08/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class RepositoryServiceTest {

    @Autowired
    RepositoryService repositoryService;

    @MockBean
    RepositoryRepository repositoryRepository;

    @MockBean
    UserRepository userRepository;

    @Test
    public void testGetRepositoriesOfUser_ShouldReturnNullWithoutAnyExceptionWhenUserDoesNotExists() {
        Mockito.when(userRepository.findSummaryByLogin(Mockito.anyString()))
                .thenReturn(null);
        try {
            List<Repository> list = repositoryService.getRepositoriesOfUser("turgayilmaz");
            assertNull(list);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testGetRepositoriesOfUser_ShouldReturnEmptyListWithoutAnyException_WhenNoRepoExists() {
        UserProfileSummary summary = new UserProfileSummary("turgayilmaz", 1l, null, null, UserType.User, false);
        Mockito.when(userRepository.findSummaryByLogin(Mockito.anyString()))
                .thenReturn(summary);

        Stream<RepositoryCore> stream = new ArrayList<RepositoryCore>().stream();
        Mockito.when(repositoryRepository.findRepositoriesByOwner_Login(Mockito.anyString()))
                .thenReturn(stream);

        try {
            List<Repository> list = repositoryService.getRepositoriesOfUser("turgayilmaz");
            assertEquals(0, list.size());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testGetRepository() {
        UserSummary owner = new UserSummary();
        RepositoryCore repositoryCore = new RepositoryCore();
        repositoryCore.setId(1l);
        Repository repository = repositoryService.constructNewRepository("turgayilmaz", owner, repositoryCore);

        assertEquals(repositoryCore, repository.getRepositoryCore());
        assertEquals(owner, repository.getOwner());
        assertNotNull(repository.getRepositoryApiUrls());
        assertNotNull(repository.getRepositoryVcsUrls());
        assertNotNull(repository.getRepositoryStatistics());
    }

    @Test
    public void testGetOwner_ShouldReturnNull_WhenUserDoesNotExist() {
        Mockito.when(userRepository.findSummaryByLogin(Mockito.anyString()))
                .thenReturn(null);
        UserSummary userSummary = repositoryService.getOwner("turgayilmaz");
        assertNull(userSummary);
    }

    @Test
    public void testGetOwner_ShouldReturnConsistentData_WhenUserExists() {
        UserProfileSummary userProfileSummary = new UserProfileSummary("turgayilmaz", 1l, null, null, UserType.User, false);
        Mockito.when(userRepository.findSummaryByLogin(Mockito.anyString())).thenReturn(userProfileSummary);

        UserSummary userSummary = repositoryService.getOwner("turgayilmaz");

        assertEquals(userProfileSummary.getLogin(), userSummary.getUserProfileSummary().getLogin());
        assertEquals(userProfileSummary.getId(), userSummary.getUserProfileSummary().getId());
        assertNotNull(userSummary.getUserApiUrls());
    }

    @Test
    public void testCreateRepositoryApiUrls() {
        RepositoryApiUrls urls = repositoryService.createRepositoryApiUrls("turgayilmaz", "xyz-code-challenge");
        String[][] urlData = getUrlData();
        for (String[] row : urlData) {
            String result = invokeMethod(row[0], urls);
            assertEquals("Urls are different for " + row[0], row[1], result);
        }
    }


    private String[][] getUrlData() {
        return new String[][]{
                {"html_url", "https://github.com/turgayilmaz/xyz-code-challenge"},
                {"url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge"},
                {"forks_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/forks"},
                {"keys_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/keys{/key_id}"},
                {"collaborators_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/collaborators{/collaborator}"},
                {"teams_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/teams"},
                {"hooks_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/hooks"},
                {"issue_events_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/issues/events{/number}"},
                {"events_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/events"},
                {"assignees_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/assignees{/user}"},
                {"branches_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/branches{/branch}"},
                {"tags_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/tags"},
                {"blobs_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/git/blobs{/sha}"},
                {"git_tags_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/git/tags{/sha}"},
                {"git_refs_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/git/refs{/sha}"},
                {"trees_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/git/trees{/sha}"},
                {"statuses_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/statuses/{sha}"},
                {"languages_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/languages"},
                {"stargazers_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/stargazers"},
                {"contributors_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/contributors"},
                {"subscribers_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/subscribers"},
                {"subscription_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/subscription"},
                {"commits_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/commits{/sha}"},
                {"git_commits_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/git/commits{/sha}"},
                {"comments_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/comments{/number}"},
                {"issue_comment_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/issues/comments{/number}"},
                {"contents_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/contents/{+path}"},
                {"compare_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/compare/{base}...{head}"},
                {"merges_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/merges"},
                {"archive_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/{archive_format}{/ref}"},
                {"downloads_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/downloads"},
                {"issues_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/issues{/number}"},
                {"pulls_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/pulls{/number}"},
                {"milestones_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/milestones{/number}"},
                {"notifications_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/notifications{?since,all,participating}"},
                {"labels_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/labels{/name}"},
                {"releases_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/releases{/id}"},
                {"deployments_url", "https://api.github.com/repos/turgayilmaz/xyz-code-challenge/deployments"}
        };
    }

    @Test
    public void testCreateRepositoryVcsUrls() {
        RepositoryVcsUrls urls = repositoryService.createRepositoryVcsUrls("turgayilmaz/xyz-code-challenge");
        String[][] urlData = getVcsData();
        for (String[] row : urlData) {
            String result = invokeMethod(row[0], urls);
            assertEquals("Urls are different for " + row[0], row[1], result);
        }
    }

    private String[][] getVcsData() {
        return new String[][]{
                {"git_url", "git://github.com/turgayilmaz/xyz-code-challenge.git"},
                {"ssh_url", "git@github.com:turgayilmaz/xyz-code-challenge.git"},
                {"clone_url", "https://github.com/turgayilmaz/xyz-code-challenge.git"},
                {"svn_url", "https://github.com/turgayilmaz/xyz-code-challenge"}
        };
    }

    @Test(expected = InvalidArgumentException.class)
    public void testCreateRepositoryForUser_ShouldThrowException_WhenRepositoryIsNull() throws InvalidArgumentException {
        repositoryService.createRepositoryForUser("turgayilmaz", null);
        fail("Exception should be thrown");
    }

    @Test(expected = InvalidArgumentException.class)
    public void testCreateRepositoryForUser_ShouldThrowException_WhenRepositoryNameIsNull() throws InvalidArgumentException {
        RepositoryCore userProfile = new RepositoryCore();
        repositoryService.createRepositoryForUser("turgayilmaz", userProfile);
        fail("Exception should be thrown");
    }

    @Test()
    public void testCreateRepositoryForUser_ShouldSave_WhenParametersAreCorrect() throws InvalidArgumentException {
        UserProfileSummary mockUserProfileSummary = new UserProfileSummary("turgayilmaz", 99l, null, null, UserType.User, false);

        RepositoryCore repo = new RepositoryCore();
        repo.setName("repo-name");

        RepositoryCore savedRepo = new RepositoryCore();
        savedRepo.setName("repo-name");
        savedRepo.setId(101l);

        List<RepositoryCore> list = new ArrayList<>();
        list.add(savedRepo);

        Mockito.when(userRepository.findIdByLogin(Mockito.anyString())).thenReturn(99l);
        Mockito.when(userRepository.findSummaryByLogin(Mockito.anyString())).thenReturn(mockUserProfileSummary);
        Mockito.when(repositoryRepository.save(repo)).thenReturn(savedRepo);
        Mockito.when(repositoryRepository.findRepositoriesByOwner_Login("turgayilmaz")).thenReturn(list.stream());

        repositoryService.createRepositoryForUser("turgayilmaz", repo);
        List<Repository> repos = repositoryService.getRepositoriesOfUser("turgayilmaz");
        assertNotNull(repos);
        assertEquals(1, repos.size());
    }
}