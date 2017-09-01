package com.twilllogistics.assessment.twilluserapi.data;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.twilllogistics.assessment.twilluserapi.pojo.RepositoryApiUrls;
import com.twilllogistics.assessment.twilluserapi.pojo.RepositoryCore;
import com.twilllogistics.assessment.twilluserapi.pojo.RepositoryStatistics;
import com.twilllogistics.assessment.twilluserapi.pojo.RepositoryVcsUrls;

/**
 * Created by turgay on 31/08/17.
 * <p>
 * Class representing the User Repository data.
 */
public class Repository {

    private UserSummary owner;

    @JsonUnwrapped
    private RepositoryCore repositoryCore;

    @JsonUnwrapped
    private RepositoryApiUrls repositoryApiUrls;

    @JsonUnwrapped
    private RepositoryVcsUrls repositoryVcsUrls;

    @JsonUnwrapped
    private RepositoryStatistics repositoryStatistics;

    public UserSummary getOwner() {
        return owner;
    }

    public void setOwner(UserSummary owner) {
        this.owner = owner;
    }

    public RepositoryCore getRepositoryCore() {
        return repositoryCore;
    }

    public void setRepositoryCore(RepositoryCore repositoryCore) {
        this.repositoryCore = repositoryCore;
    }

    public RepositoryApiUrls getRepositoryApiUrls() {
        return repositoryApiUrls;
    }

    public void setRepositoryApiUrls(RepositoryApiUrls repositoryApiUrls) {
        this.repositoryApiUrls = repositoryApiUrls;
    }

    public RepositoryVcsUrls getRepositoryVcsUrls() {
        return repositoryVcsUrls;
    }

    public void setRepositoryVcsUrls(RepositoryVcsUrls repositoryVcsUrls) {
        this.repositoryVcsUrls = repositoryVcsUrls;
    }

    public RepositoryStatistics getRepositoryStatistics() {
        return repositoryStatistics;
    }

    public void setRepositoryStatistics(RepositoryStatistics repositoryStatistics) {
        this.repositoryStatistics = repositoryStatistics;
    }
}
