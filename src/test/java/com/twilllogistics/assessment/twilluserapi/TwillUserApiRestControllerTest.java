package com.twilllogistics.assessment.twilluserapi;

import com.twilllogistics.assessment.twilluserapi.data.Repository;
import com.twilllogistics.assessment.twilluserapi.data.User;
import com.twilllogistics.assessment.twilluserapi.data.UserType;
import com.twilllogistics.assessment.twilluserapi.exception.InvalidArgumentException;
import com.twilllogistics.assessment.twilluserapi.pojo.RepositoryCore;
import com.twilllogistics.assessment.twilluserapi.pojo.UserApiUrls;
import com.twilllogistics.assessment.twilluserapi.pojo.UserProfile;
import com.twilllogistics.assessment.twilluserapi.pojo.UserStatistics;
import com.twilllogistics.assessment.twilluserapi.service.RepositoryService;
import com.twilllogistics.assessment.twilluserapi.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by turgay on 22/08/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = TwillUserApiRestController.class, secure = false)
public class TwillUserApiRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private RepositoryService repositoryService;

    @Test
    public void testGetUserByUserName_ShouldReturnHTTP200AndUserData_WhenUserExists() throws Exception {
        Mockito.when(userService.getUserByUserName("turgayilmaz")).thenReturn(new User());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{username}", "turgayilmaz").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void testGetUserByUserName_ShouldReturnHTTP404AndExceptionMessage_WhenUserDoesNotExists() throws Exception {
        String expected = "{ \"message\": \"User Not Found\", \"documentation_url\": \"https://api.github.com/documentation\" }";
        Mockito.when(userService.getUserByUserName("turgayilmaz")).thenReturn(null);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{username}", "turgayilmaz").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void testCreateUser_ShouldReturn400_WithInadequateInput() throws Exception {

        Mockito.doThrow(InvalidArgumentException.class).when(userService).createUser(Mockito.any(UserProfile.class));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users")
                .accept(MediaType.APPLICATION_JSON).content("{}")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void testCreateUser_ShouldReturn200_WithAdequateInput() throws Exception {

        Mockito.doNothing().when(userService).createUser(Mockito.any(UserProfile.class));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users")
                .accept(MediaType.APPLICATION_JSON).content("{}")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void testGetRepositoriesOfUser_ShouldReturnHTTP200AndRepoData_WhenUserExists() throws Exception {
        Mockito.when(repositoryService.getRepositoriesOfUser("turgayilmaz")).thenReturn(new ArrayList<Repository>());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{username}/repos", "turgayilmaz").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void testGetRepositoriesOfUser_ShouldReturnHTTP404AndExceptionMessage_WhenUserDoesNotExists() throws Exception {
        String expected = "{ \"message\": \"User Not Found\", \"documentation_url\": \"https://api.github.com/documentation\" }";
        Mockito.when(repositoryService.getRepositoriesOfUser("turgayilmaz")).thenReturn(null);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{username}/repos", "turgayilmaz").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

    }

    @Test
    public void testCreateRepositoryForUser_ShouldReturn400_WithInadequateInput() throws Exception {

        Mockito.doThrow(InvalidArgumentException.class).when(repositoryService)
                .createRepositoryForUser(Mockito.anyString(), Mockito.any(RepositoryCore.class));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/{username}/repos", "turgayilmaz")
                .accept(MediaType.APPLICATION_JSON).content("{}")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void testCreateRepositoryUser_ShouldReturn200_WithAdequateInput() throws Exception {

        Mockito.doNothing().when(repositoryService).createRepositoryForUser(Mockito.anyString(), Mockito.any(RepositoryCore.class));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/users/{username}/repos", "turgayilmaz")
                .accept(MediaType.APPLICATION_JSON).content("{}")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

}