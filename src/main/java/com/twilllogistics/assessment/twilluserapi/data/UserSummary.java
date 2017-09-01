package com.twilllogistics.assessment.twilluserapi.data;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.twilllogistics.assessment.twilluserapi.pojo.UserApiUrls;
import com.twilllogistics.assessment.twilluserapi.pojo.UserProfileSummary;

/**
 * Created by turgay on 31/08/17.
 *
 * Class representing UserSummary data.
 * This class is required since get REST services related to User and Repository objects differs.
 * User get REST service returns all of the user fields (user profile, user api urls and user statistics),
 * while Repository get REST service does not include user statistics related fields. In addition,
 * Repository get service does not include all of the user profile attributes, thus a different POJO class
 * (UserProfileSummary) is also introduces, which includes the almost half of the fields.
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
