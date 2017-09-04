package com.twilllogistics.assessment.twilluserapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.twilllogistics.assessment.twilluserapi.data.ExceptionMessage;
import com.twilllogistics.assessment.twilluserapi.data.Repository;
import com.twilllogistics.assessment.twilluserapi.data.User;
import com.twilllogistics.assessment.twilluserapi.exception.InvalidArgumentException;
import com.twilllogistics.assessment.twilluserapi.pojo.RepositoryCore;
import com.twilllogistics.assessment.twilluserapi.pojo.UserProfile;
import com.twilllogistics.assessment.twilluserapi.service.RepositoryService;
import com.twilllogistics.assessment.twilluserapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by turgay on 31/07/17.
 * <p>
 * Rest controller for Twill User API operations.
 */
@RestController
public class TwillUserApiRestController {

    @Autowired
    UserService userService;

    @Autowired
    RepositoryService repositoryService;

    @Value("${documentation.url}")
    String documentationUrl;

    /**
     * REST service for querying users
     * @param username User login name
     * @return A User object if user if found (See data structure at https://api.github.com/users/{USERNAME}),
     *         or a 404 response along with Exception message if user is not found.
     */
    @RequestMapping(path = "/users/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserByUserName(@PathVariable String username) {
        User user = userService.getUserByUserName(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return getUserNotFoundResponse();
        }
    }

    /**
     * REST service for creating users
     * @param userProfile UserProfile object to be saved, which includes the core fields of the User object
     *  <pre>
     *     {
     *       "login": "turgayilmaz",
     *       "id": 31064481,
     *       "avatar_url": "https://avatars2.githubusercontent.com/u/31064481?v=4",
     *       "gravatar_id": "",
     *       "type": "User",
     *       "site_admin": false,
     *       "name": null,
     *       "company": null,
     *       "blog": "",
     *       "location": null,
     *       "email": null,
     *       "hireable": null,
     *       "bio": null,
     *       "created_at": "2017-08-16T09:27:27Z",
     *       "updated_at": "2017-08-28T13:28:43Z"
     *      }
     *  </pre>
     * @return An HTTP 200 response if succesfully saved,
     *         or an HTTP 400 bad request response if given UserProfile object or the user login name is empty
     */
    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody UserProfile userProfile) {
        try {
            userService.createUser(userProfile);
        } catch (InvalidArgumentException e) {
            return getBadRequestResponseEntity(e);
        }
        return ResponseEntity.ok().build();
    }

    /**
     * REST service for querying repositories of a user
     * @param username User login name
     * @return List of Repository objects if user if found,
     *         an empty list if the user has no repositories,
     *         or a 404 response along with Exception message if user is not found.
     */
    @RequestMapping(path = "/users/{username}/repos", method = RequestMethod.GET)
    public ResponseEntity<?> getRepositoriesOfUser(@PathVariable String username) {
        List<Repository> repositories = repositoryService.getRepositoriesOfUser(username);
        if (repositories != null) {
            return ResponseEntity.ok(repositories);
        } else {
            return getUserNotFoundResponse();
        }

    }

    /**
     * REST service for creating repositories for a user
     * @param username User login name
     * @param repository RepositoryCore object to be saved, which includes the core fields of the Repository object
     * @return An HTTP 200 response if succesfully saved,
     *         or an HTTP 400 bad request response
     *           if given RepositoryCore object is empty, repository name is empty,
     *            or the user login name is empty / not found in dDB
     */
    @RequestMapping(path = "/users/{username}/repos", method = RequestMethod.POST)
    public ResponseEntity<?> createRepositoryForUser(@PathVariable String username, @RequestBody RepositoryCore repository) {
        try {
            repositoryService.createRepositoryForUser(username, repository);
        } catch (InvalidArgumentException e) {
            return getBadRequestResponseEntity(e);
        }
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<?> getUserNotFoundResponse() {
        ExceptionMessage exceptionMessage = new ExceptionMessage("User Not Found", documentationUrl);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionMessage);
    }

    private ResponseEntity<?> getBadRequestResponseEntity(InvalidArgumentException e) {
        ExceptionMessage exceptionMessage = new ExceptionMessage(e.getMessage(), documentationUrl);
        return ResponseEntity.badRequest().body(exceptionMessage);
    }

    /**
     * JSON conversion configurator
     * According the given API requirements, the dates has to be in ISO8601 format.
     */
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(new ISO8601DateFormat());

        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
    }

}
