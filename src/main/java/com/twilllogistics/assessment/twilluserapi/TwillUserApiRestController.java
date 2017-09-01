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

    @RequestMapping(path = "/users/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserByUserName(@PathVariable String username) {
        User user = userService.getUserByUserName(username);
        if (user!=null) {
            return ResponseEntity.ok(user);
        } else {
            return getUserNotFoundResponse();
        }
    }

    private ResponseEntity<?> getUserNotFoundResponse() {
        ExceptionMessage exceptionMessage = new ExceptionMessage("User Not Found", documentationUrl);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionMessage);
    }

    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody UserProfile userProfile) {
        try {
            userService.createUser(userProfile);
        } catch (InvalidArgumentException e) {
            return getBadRequestResponseEntity(e);
        }
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<?> getBadRequestResponseEntity(InvalidArgumentException e) {
        ExceptionMessage exceptionMessage = new ExceptionMessage(e.getMessage(), documentationUrl);
        return ResponseEntity.badRequest().body(exceptionMessage);
    }

    @RequestMapping(path = "/users/{username}/repos", method = RequestMethod.GET)
    public ResponseEntity<?> getRepositoriesOfUser(@PathVariable String username) {
        List<Repository> repositories = repositoryService.getRepositoriesOfUser(username);
        if (repositories!=null) {
            return ResponseEntity.ok(repositories);
        } else {
            return getUserNotFoundResponse();
        }

    }

    @RequestMapping(path = "/users/{username}/repos", method = RequestMethod.POST)
    public ResponseEntity<?> createRepositoryForUser(@PathVariable String username, @RequestBody RepositoryCore repository) {
        try {
            repositoryService.createRepositoryForUser(username, repository);
        } catch (InvalidArgumentException e) {
            return getBadRequestResponseEntity(e);
        }
        return ResponseEntity.ok().build();
    }

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
