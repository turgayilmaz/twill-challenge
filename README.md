# How to Compile And Run
- The project is a Spring-Boot project. 
    - It can compiled with Maven, and then the produced war file can be deployed to an application server,
    - Or simply, **mvnw spring-boot:run** command can be executed on the project home folder, to compile, package and run.
- After running the server, the services can be consumed at the following adresses:
    - Create User (POST): http://localhost:8080/users        
    - Get User (GET): http://localhost:8080/users/{username}
    - Create Repository (POST): http://localhost:8080/users/{username}/repos
    - Get Repositories (GET): http://localhost:8080/users/{username}/repos
- Implemented rest services are fully compliant with the following data scructures:
    - https://api.github.com/users/USERNAME
    - https://api.github.com/users/USERNAME/repos

# API 

### Create User (POST): 

**Sample Request URL:** http://localhost:8080/users

**Sample Request:**
```javascript
{
  "login": "turgayilmaz",
  "id": 31064481,
  "avatar_url": "https://avatars2.githubusercontent.com/u/31064481?v=4",
  "gravatar_id": "",
  "type": "User",
  "site_admin": false,
  "name": null,
  "company": null,
  "blog": "",
  "location": null,
  "email": null,
  "hireable": null,
  "bio": null,
  "created_at": "2017-08-16T09:27:27Z",
  "updated_at": "2017-08-28T13:28:43Z"
}
```

**Sample Response(Successful):**
`HTTP 200`

**Sample Response(Error):**
`HTTP 400`
```javascript
{
    "message": "UserProfile.login should not be null",
    "documentation_url": "https://api.twill-logistics.com/documentation"
}
```

### Get User (GET): 

**Sample Request URL:** http://localhost:8080/users/turgayilmaz

**Sample Response(Successful):**
`HTTP 200`
```javascript
{
    "login": "turgayilmaz",
    "id": 1,
    "avatar_url": "https://avatars2.githubusercontent.com/u/31064481?v=4",
    "gravatar_id": "",
    "type": "User",
    "site_admin": false,
    "name": null,
    "company": null,
    "blog": "",
    "location": null,
    "email": null,
    "hireable": null,
    "bio": null,
    "created_at": "2017-08-16T09:27:27Z",
    "updated_at": "2017-08-28T13:28:43Z",
    "url": "https://api.twill-logistics.com/users/turgayilmaz",
    "html_url": "https://twill-logistics.com/turgayilmaz",
    "followers_url": "https://api.twill-logistics.com/users/turgayilmaz/followers",
    "following_url": "https://api.twill-logistics.com/users/turgayilmaz/following{/other_user}",
    "gists_url": "https://api.twill-logistics.com/users/turgayilmaz/gists{/gist_id}",
    "starred_url": "https://api.twill-logistics.com/users/turgayilmaz/starred{/owner}{/repo}",
    "subscriptions_url": "https://api.twill-logistics.com/users/turgayilmaz/subscriptions",
    "organizations_url": "https://api.twill-logistics.com/users/turgayilmaz/orgs",
    "repos_url": "https://api.twill-logistics.com/users/turgayilmaz/repos",
    "events_url": "https://api.twill-logistics.com/users/turgayilmaz/events{/privacy}",
    "received_events_url": "https://api.twill-logistics.com/users/turgayilmaz/received_events",
    "public_repos": 0,
    "public_gists": 1761283695,
    "followers": 325,
    "following": 9176
}
```

**Sample Response(Error):**
`HTTP 404`
```javascript
{
     "message": "User Not Found",
     "documentation_url": "https://api.twill-logistics.com/documentation"
}
```

### Create Repository (POST):

**Sample Request URL:** http://localhost:8080/users/turgayilmaz/repos

**Sample Request:**
```javascript
{
     "id": 101068836,
     "name": "n26-code-challenge",
     "private": false,
     "description": "N26 Code Challenge",
     "fork": false,
     "language": "Java",
     "homepage": null,
     "mirror_url": null,
     "default_branch": "master",
     "created_at": "2017-08-22T13:57:59Z",
     "updated_at": "2017-08-22T14:07:23Z",
     "pushed_at": "2017-08-22T14:38:49Z"
 }
```

**Sample Response(Successful):**
`HTTP 200`

**Sample Response(Error):**
`HTTP 400`
```javascript
{
     "message": "Repository.name should not be null",
     "documentation_url": "https://api.twill-logistics.com/documentation"
}
``` 

### Get Repositories (GET):

**Sample Request URL:** http://localhost:8080/users/turgayilmaz/repos

**Sample Response(Successful):**
`HTTP 200`
```javascript
[
     {
         "owner": {
             "login": "turgayilmaz",
             "id": 1,
             "avatar_url": "https://avatars2.githubusercontent.com/u/31064481?v=4",
             "gravatar_id": "",
             "type": "User",
             "site_admin": false,
             "url": "https://api.twill-logistics.com/users/turgayilmaz",
             "html_url": "https://twill-logistics.com/turgayilmaz",
             "followers_url": "https://api.twill-logistics.com/users/turgayilmaz/followers",
             "following_url": "https://api.twill-logistics.com/users/turgayilmaz/following{/other_user}",
             "gists_url": "https://api.twill-logistics.com/users/turgayilmaz/gists{/gist_id}",
             "starred_url": "https://api.twill-logistics.com/users/turgayilmaz/starred{/owner}{/repo}",
             "subscriptions_url": "https://api.twill-logistics.com/users/turgayilmaz/subscriptions",
             "organizations_url": "https://api.twill-logistics.com/users/turgayilmaz/orgs",
             "repos_url": "https://api.twill-logistics.com/users/turgayilmaz/repos",
             "events_url": "https://api.twill-logistics.com/users/turgayilmaz/events{/privacy}",
             "received_events_url": "https://api.twill-logistics.com/users/turgayilmaz/received_events"
         },
         "id": 1,
         "name": "n26-code-challenge",
         "description": "N26 Code Challenge",
         "fork": false,
         "language": "Java",
         "homepage": null,
         "mirror_url": null,
         "default_branch": "master",
         "created_at": "2017-08-22T13:57:59Z",
         "updated_at": "2017-08-22T14:07:23Z",
         "pushed_at": "2017-08-22T14:38:49Z",
         "private": false,
         "full_name": "turgayilmaz/n26-code-challenge",
         "html_url": "https://twill-logistics.com/turgayilmaz/n26-code-challenge",
         "url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge",
         "forks_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/forks",
         "keys_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/keys{/key_id}",
         "collaborators_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/collaborators{/collaborator}",
         "teams_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/teams",
         "hooks_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/hooks",
         "issue_events_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/issues/events{/number}",
         "events_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/events",
         "assignees_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/assignees{/user}",
         "branches_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/branches{/branch}",
         "tags_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/tags",
         "blobs_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/git/blobs{/sha}",
         "git_tags_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/git/tags{/sha}",
         "git_refs_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/git/refs{/sha}",
         "trees_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/git/trees{/sha}",
         "statuses_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/statuses/{sha}",
         "languages_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/languages",
         "stargazers_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/stargazers",
         "contributors_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/contributors",
         "subscribers_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/subscribers",
         "subscription_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/subscription",
         "commits_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/commits{/sha}",
         "git_commits_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/git/commits{/sha}",
         "comments_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/comments{/number}",
         "issue_comment_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/issues/comments{/number}",
         "contents_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/contents/{+path}",
         "compare_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/compare/{base}...{head}",
         "merges_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/merges",
         "archive_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/{archive_format}{/ref}",
         "downloads_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/downloads",
         "issues_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/issues{/number}",
         "pulls_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/pulls{/number}",
         "milestones_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/milestones{/number}",
         "notifications_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/notifications{?since,all,participating}",
         "labels_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/labels{/name}",
         "releases_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/releases{/id}",
         "deployments_url": "https://api.twill-logistics.com/repos/turgayilmaz/n26-code-challenge/deployments",
         "git_url": "git://twill-logistics.com/turgayilmaz/n26-code-challenge.git",
         "ssh_url": "git@twill-logistics.com:turgayilmaz/n26-code-challenge.git",
         "clone_url": "https://twill-logistics.com/turgayilmaz/n26-code-challenge.git",
         "svn_url": "https://twill-logistics.com/turgayilmaz/n26-code-challenge",
         "size": -5335911890708011662,
         "stargazers_count": 655996946,
         "watchers_count": 685382526,
         "has_issues": false,
         "has_projects": false,
         "has_downloads": false,
         "has_wiki": false,
         "has_pages": false,
         "forks_count": 431529176,
         "open_issues_count": -138487339,
         "forks": -1155869325,
         "open_issues": -1465154083,
         "watchers": -155886662
     }
 ]
```

**Sample Response(Error):**
`HTTP 404`
```javascript
{
     "message": "User Not Found",
     "documentation_url": "https://api.twill-logistics.com/documentation"
}
```

# Design Decisions / Assumptions
- A simple analysis on the User and Repository data shows that;
    - User data includes three types of fields: (i) Core user fields, (ii) API URL adresses to get other information related to the user, and (iii) Some statistics data including number of repos, etc.
    - Repository data includes five types of fields: (i) Core repository fields, (ii) Owner of the data, (iii) API URL adresses to get other information related to the repo, (iv) VCS related addresses including git and svn, and (v) Some statistics data related to the repository.
    - Considering above given analysis;
        - User and Repository data classes are designed as including such compositions.
        - Core fields are stored in the database
        - URL adresses are auto-generated, not stored
        - Statistics fields are assumed to be queried from database, but tables for these are not included. Thus, some mock implementations are given.
- Considering that _create user_ and _create repo_ services do not need objects properties as _get_ services (for example; above given url based properties for a user, or the user details (owner property) of a repo), _create_ services are designed in a more compact fashion.
- Considering that this is not a production system, no caching mechanism is included for frequently used data. Potential candidates are; user login name to user DB id cache, statistics data, etc.
- It is assumed that login field of User data should not be null. Required implementations are included. 
- It is assumed that name field of Repository data should not be null. Required implementations are included. 
- Since there are not much business requirements, a small number of unit tests (according to above given assumptions/considerations) are included.

# Other Notes
- Although it is stated that database is not necessary, a simple H2 database with simple JPA repositories are included for a complete solution.
- Project is constructed with spring-boot for simple configuration
- Java 8 features are included where applicable (for instance, Stream usage in RepositoryService)
- In total it took about 6-7 hours to complete, including tests, comments and README documentation. (Sorry for spending more than you expect)
- For simplicity of the API tests, only the HTTP status reponses are checked, JSON contents are not checked.