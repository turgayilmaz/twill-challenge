package com.twilllogistics.assessment.twilluserapi.pojo;

/**
 * Created by turgay on 31/08/17.
 *
 * POJO for representing the statistics and extra information on User data.
 * It is assumed that this data is collected by using several other tables from the database.
 */
public class UserStatistics {

    private Integer public_repos;

    private Integer public_gists;

    private Integer followers;

    private Integer following;

    public Integer getPublic_repos() {
        return public_repos;
    }

    public void setPublic_repos(Integer public_repos) {
        this.public_repos = public_repos;
    }

    public Integer getPublic_gists() {
        return public_gists;
    }

    public void setPublic_gists(Integer public_gists) {
        this.public_gists = public_gists;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public Integer getFollowing() {
        return following;
    }

    public void setFollowing(Integer following) {
        this.following = following;
    }
}
