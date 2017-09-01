package com.twilllogistics.assessment.twilluserapi.pojo;

/**
 * Created by turgay on 31/08/17.
 *
 * POJO for representing the statistics and extra information on Repository data.
 * It is assumed that this data is collected by using several other tables from the database.
 */
public class RepositoryStatistics {

    private Long size;
    private Integer stargazers_count;
    private Integer watchers_count;
    private Boolean has_issues;
    private Boolean has_projects;
    private Boolean has_downloads;
    private Boolean has_wiki;
    private Boolean has_pages;
    private Integer forks_count;
    private Integer open_issues_count;
    private Integer forks;
    private Integer open_issues;
    private Integer watchers;

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Integer getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(Integer stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public Integer getWatchers_count() {
        return watchers_count;
    }

    public void setWatchers_count(Integer watchers_count) {
        this.watchers_count = watchers_count;
    }

    public Boolean getHas_issues() {
        return has_issues;
    }

    public void setHas_issues(Boolean has_issues) {
        this.has_issues = has_issues;
    }

    public Boolean getHas_projects() {
        return has_projects;
    }

    public void setHas_projects(Boolean has_projects) {
        this.has_projects = has_projects;
    }

    public Boolean getHas_downloads() {
        return has_downloads;
    }

    public void setHas_downloads(Boolean has_downloads) {
        this.has_downloads = has_downloads;
    }

    public Boolean getHas_wiki() {
        return has_wiki;
    }

    public void setHas_wiki(Boolean has_wiki) {
        this.has_wiki = has_wiki;
    }

    public Boolean getHas_pages() {
        return has_pages;
    }

    public void setHas_pages(Boolean has_pages) {
        this.has_pages = has_pages;
    }

    public Integer getForks_count() {
        return forks_count;
    }

    public void setForks_count(Integer forks_count) {
        this.forks_count = forks_count;
    }

    public Integer getOpen_issues_count() {
        return open_issues_count;
    }

    public void setOpen_issues_count(Integer open_issues_count) {
        this.open_issues_count = open_issues_count;
    }

    public Integer getForks() {
        return forks;
    }

    public void setForks(Integer forks) {
        this.forks = forks;
    }

    public Integer getOpen_issues() {
        return open_issues;
    }

    public void setOpen_issues(Integer open_issues) {
        this.open_issues = open_issues;
    }

    public Integer getWatchers() {
        return watchers;
    }

    public void setWatchers(Integer watchers) {
        this.watchers = watchers;
    }
}
