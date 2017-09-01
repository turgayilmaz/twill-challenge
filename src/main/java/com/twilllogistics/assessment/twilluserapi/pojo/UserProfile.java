package com.twilllogistics.assessment.twilluserapi.pojo;

import com.twilllogistics.assessment.twilluserapi.data.UserType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by turgay on 31/08/17.
 *
 * POJO class for the core user fields.
 * For creating a new User these fields are expected from the user.
 */
@Entity
public class UserProfile {

    private String login;

    @Id
    @GeneratedValue
    private Long id;

    private String avatar_url;

    private String gravatar_id;

    private UserType type = UserType.User;

    private Boolean site_admin;

    private String name;

    private String company;

    private String blog;

    private String location;

    private String email;

    private Boolean hireable;

    private String bio;

    private Date created_at;

    private Date updated_at;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getHireable() {
        return hireable;
    }

    public void setHireable(Boolean hireable) {
        this.hireable = hireable;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
