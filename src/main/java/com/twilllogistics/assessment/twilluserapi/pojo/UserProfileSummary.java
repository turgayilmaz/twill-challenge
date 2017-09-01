package com.twilllogistics.assessment.twilluserapi.pojo;

import com.twilllogistics.assessment.twilluserapi.data.UserType;

/**
 * Created by turgay on 31/08/17.
 * <p>
 * This class is used for representing a small set of UserProfile fields in the Repository object
 */
public class UserProfileSummary {

    private String login;

    private Long id;

    private String avatar_url;

    private String gravatar_id;

    private UserType type;

    private Boolean site_admin;

    public UserProfileSummary(String login, Long id, String avatar_url, String gravatar_id, UserType type, Boolean site_admin) {
        this.login = login;
        this.id = id;
        this.avatar_url = avatar_url;
        this.gravatar_id = gravatar_id;
        this.type = type;
        this.site_admin = site_admin;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getGravatar_id() {
        return gravatar_id;
    }

    public void setGravatar_id(String gravatar_id) {
        this.gravatar_id = gravatar_id;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public Boolean getSite_admin() {
        return site_admin;
    }

    public void setSite_admin(Boolean site_admin) {
        this.site_admin = site_admin;
    }

}
