package com.twilllogistics.assessment.twilluserapi.data;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.twilllogistics.assessment.twilluserapi.pojo.UserApiUrls;
import com.twilllogistics.assessment.twilluserapi.pojo.UserProfileSummary;

/**
 * Created by turgay on 31/08/17.
 *
 * Class representing UserSummary data.
 * User Summary data is used for including the a small set of user attributes
 * withing the Repository object.
 */
public class UserSummary {

    @JsonUnwrapped
    private UserProfileSummary userProfileSummary;

    @JsonUnwrapped
    private UserApiUrls userApiUrls;

    public UserProfileSummary getUserProfileSummary() {
        return userProfileSummary;
    }

    public void setUserProfileSummary(UserProfileSummary userProfileSummary) {
        this.userProfileSummary = userProfileSummary;
    }

    public UserApiUrls getUserApiUrls() {
        return userApiUrls;
    }

    public void setUserApiUrls(UserApiUrls userApiUrls) {
        this.userApiUrls = userApiUrls;
    }
}
