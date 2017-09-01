package com.twilllogistics.assessment.twilluserapi.service;

import com.twilllogistics.assessment.twilluserapi.data.User;
import com.twilllogistics.assessment.twilluserapi.data.UserType;
import com.twilllogistics.assessment.twilluserapi.exception.InvalidArgumentException;
import com.twilllogistics.assessment.twilluserapi.pojo.UserApiUrls;
import com.twilllogistics.assessment.twilluserapi.pojo.UserProfile;
import com.twilllogistics.assessment.twilluserapi.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static com.twilllogistics.assessment.twilluserapi.service.TestHelper.invokeMethod;
import static org.junit.Assert.*;

/**
 * Created by turgay on 31/08/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @Test
    public void testGetUserByUserName_ShouldReturnNullWithoutExceptions_WhenNoUserExists() {
        Mockito.when(userRepository.findByLogin(Mockito.anyString()))
                .thenReturn(null);

        User user = null;
        try {
            user = userService.getUserByUserName("turgay");
        } catch (Exception e) {
            fail("Unknown user should not cause an exception!");
        }
        assertNull("Return value should be null.", user);
    }

    @Test
    public void testGetUserByUserName_ShouldWorkConsistenly_WhenUserExists() {
        UserProfile userProfile = new UserProfile();
        userProfile.setLogin("turgay");
        userProfile.setId(99l);

        Mockito.when(userRepository.findByLogin(Mockito.anyString()))
                .thenReturn(userProfile);

        User user = userService.getUserByUserName("turgay");
        assertNotNull(user);
        assertEquals(userProfile.getLogin(), user.getUserProfile().getLogin());
        assertNotNull(user.getUserApiUrls());
        assertNotNull(user.getUserStatistics());
    }

    @Test
    public void testCreateUserApiUrlWithLoginName() {
        UserApiUrls urls = userService.createUserApiUrlWithLoginName("turgayilmaz");
        String[][] urlData = getUrlData();
        for (String[] row : urlData) {
            String result = invokeMethod(row[0], urls);
            assertEquals("Urls are different for " + row[0], row[1], result);
        }

    }

    private String[][] getUrlData() {
        return new String[][]{
                {"url", "https://api.github.com/users/turgayilmaz"},
                {"html_url", "https://github.com/turgayilmaz"},
                {"followers_url", "https://api.github.com/users/turgayilmaz/followers"},
                {"following_url", "https://api.github.com/users/turgayilmaz/following{/other_user}"},
                {"gists_url", "https://api.github.com/users/turgayilmaz/gists{/gist_id}"},
                {"starred_url", "https://api.github.com/users/turgayilmaz/starred{/owner}{/repo}"},
                {"subscriptions_url", "https://api.github.com/users/turgayilmaz/subscriptions"},
                {"organizations_url", "https://api.github.com/users/turgayilmaz/orgs"},
                {"repos_url", "https://api.github.com/users/turgayilmaz/repos"},
                {"events_url", "https://api.github.com/users/turgayilmaz/events{/privacy}"},
                {"received_events_url", "https://api.github.com/users/turgayilmaz/received_events"}
        };
    }

    @Test(expected = InvalidArgumentException.class)
    public void testCreateUser_ShouldThrowException_WhenUserIsNull() throws InvalidArgumentException {
        userService.createUser(null);
        fail("Exception should be thrown");
    }

    @Test(expected = InvalidArgumentException.class)
    public void testCreateUser_ShouldThrowException_WhenUserLoginIsNull() throws InvalidArgumentException {
        UserProfile userProfile = new UserProfile();
        userService.createUser(userProfile);
        fail("Exception should be thrown");
    }

    @Test()
    public void testCreateUser_ShouldSave_WhenParametersAreCorrect() throws InvalidArgumentException {
        UserProfile mockUserProfile = new UserProfile();
        mockUserProfile.setLogin("turgayilmaz");

        UserProfile savedMockUserProfile = new UserProfile();
        savedMockUserProfile.setLogin("turgayilmaz");
        savedMockUserProfile.setId(99l);

        Mockito.when(userRepository.save(mockUserProfile)).thenReturn(savedMockUserProfile);
        Mockito.when(userRepository.findByLogin(Mockito.anyString())).thenReturn(savedMockUserProfile);

        userService.createUser(mockUserProfile);
        User turgayilmaz = userService.getUserByUserName("turgayilmaz");
        assertNotNull(turgayilmaz);
    }
}