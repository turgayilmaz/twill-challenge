package com.twilllogistics.assessment.twilluserapi.service;

/**
 * Created by turgay on 31/08/17.
 *
 * ApiUrlConstants class is used for holding the constants that are used in URL calculation.
 * This content of this class is also be hold in a properties file, or even in database,
 * to make it more dynamic. Howeever, these constants are not embedded directly into the service classes
 * to manage separately.
 */
public class ApiUrlConstants {

    public static final String SLASH = "/";
    public static final String USERS = "/users";
    public static final String FOLLOWERS = "/followers";
    public static final String FOLLOWING = "/following{/other_user}";
    public static final String GISTS = "/gists{/gist_id}";
    public static final String STARRED = "/starred{/owner}{/repo}";
    public static final String SUBSCRIPTIONS = "/subscriptions";
    public static final String ORGS = "/orgs";
    public static final String REPOS = "/repos";
    public static final String EVENTS_PRIVACY = "/events{/privacy}";
    public static final String RECEIVED_EVENTS = "/received_events";

    public static final String HTTPS = "https://";
    public static final String FORKS = "/forks";
    public static final String KEYS = "/keys{/key_id}";
    public static final String COLLABORATORS = "/collaborators{/collaborator}";
    public static final String TEAMS = "/teams";
    public static final String HOOKS = "/hooks";
    public static final String ISSUES_EVENTS = "/issues/events{/number}";
    public static final String EVENTS = "/events";
    public static final String ASSIGNEES = "/assignees{/user}";
    public static final String BRANCHES = "/branches{/branch}";
    public static final String TAGS = "/tags";
    public static final String GIT_BLOBS = "/git/blobs{/sha}";
    public static final String GIT_TAGS = "/git/tags{/sha}";
    public static final String GIT_REFS = "/git/refs{/sha}";
    public static final String GIT_TREES = "/git/trees{/sha}";
    public static final String STATUSES = "/statuses/{sha}";
    public static final String LANGUAGES = "/languages";
    public static final String STARGAZERS = "/stargazers";
    public static final String CONTRIBUTORS = "/contributors";
    public static final String SUBSCRIBERS = "/subscribers";
    public static final String SUBSCRIPTION = "/subscription";
    public static final String COMMITS = "/commits{/sha}";
    public static final String GIT_COMMITS = "/git/commits{/sha}";
    public static final String COMMENTS = "/comments{/number}";
    public static final String ISSUES_COMMENTS = "/issues/comments{/number}";
    public static final String CONTENTS = "/contents/{+path}";
    public static final String COMPARE = "/compare/{base}...{head}";
    public static final String MERGES = "/merges";
    public static final String ARCHIVE_FORMAT_REF = "/{archive_format}{/ref}";
    public static final String DOWNLOADS = "/downloads";
    public static final String ISSUES = "/issues{/number}";
    public static final String PULLS = "/pulls{/number}";
    public static final String MILESTONES = "/milestones{/number}";
    public static final String NOTIFICATIONS = "/notifications{?since,all,participating}";
    public static final String LABELS = "/labels{/name}";
    public static final String RELEASES = "/releases{/id}";
    public static final String DEPLOYMENTS = "/deployments";
    public static final String GIT_PROTOCOL = "git://";
    public static final String GIT_EXTENSION = ".git";
    public static final String GIT_AT = "git@";
    public static final String SEMICOLON = ":";


}
