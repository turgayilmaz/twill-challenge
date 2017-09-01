package com.twilllogistics.assessment.twilluserapi.data;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.twilllogistics.assessment.twilluserapi.pojo.UserApiUrls;
import com.twilllogistics.assessment.twilluserapi.pojo.UserProfile;
import com.twilllogistics.assessment.twilluserapi.pojo.UserStatistics;

/**
 * Created by turgay on 31/08/17.
 *
 * Class representing the User data.
 */
public class User {

    @JsonUnwrapped
    private UserProfile userProfile;

    @JsonUnwrapped
    private UserApiUrls userApiUrls;

    @JsonUnwrapped
    private UserStatistics userStatistics;

    public User(UserProfile userProfile, UserApiUrls userApiUrls, UserStatistics userStatistics) {
        this.userProfile = userProfile;
        this.userApiUrls = userApiUrls;
        this.userStatistics = userStatistics;
    }

    public User() {

    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public UserApiUrls getUserApiUrls() {
        return userApiUrls;
    }

    public void setUserApiUrls(UserApiUrls userApiUrls) {
        this.userApiUrls = userApiUrls;
    }

    public UserStatistics getUserStatistics() {
        return userStatistics;
    }

    public void setUserStatistics(UserStatistics userStatistics) {
        this.userStatistics = userStatistics;
    }
}
