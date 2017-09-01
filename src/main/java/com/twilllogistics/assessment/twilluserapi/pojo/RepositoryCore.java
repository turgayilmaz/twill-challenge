package com.twilllogistics.assessment.twilluserapi.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by turgay on 31/08/17.
 *
 * POJO class for the basic repository fields.
 * For creating a new Repository these fields are expected from the user.
 */
@Entity
public class RepositoryCore {

    @ManyToOne
    @JsonIgnore
    private UserProfile owner;

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @JsonProperty("private")
    private Boolean isPrivate;

    private String description;

    private Boolean fork;

    private String language;

    private String homepage;

    private String mirror_url;

    private String default_branch;

    private Date created_at;

    private Date updated_at;

    private Date pushed_at;

    public UserProfile getOwner() {
        return owner;
    }

    public void setOwner(UserProfile owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFork() {
        return fork;
    }

    public void setFork(Boolean fork) {
        this.fork = fork;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getMirror_url() {
        return mirror_url;
    }

    public void setMirror_url(String mirror_url) {
        this.mirror_url = mirror_url;
    }

    public String getDefault_branch() {
        return default_branch;
    }

    public void setDefault_branch(String default_branch) {
        this.default_branch = default_branch;
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

    public Date getPushed_at() {
        return pushed_at;
    }

    public void setPushed_at(Date pushed_at) {
        this.pushed_at = pushed_at;
    }
}
