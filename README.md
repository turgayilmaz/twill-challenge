#How to Compile And Run
- The project is a Spring-Boot project. 
    - It can compiled with Maven, and then the produced war file can be deployed to an application server,
    - Or simply, **mvnw spring-boot:run** command can be executed on the project home folder, to compile, package and run.
- Implemented rest services are fully compliant with thfollowing data scructures:
    - https://api.github.com/users/USERNAME
    - https://api.github.com/users/USERNAME/repos

#Design Decisions / Assumptions
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

#Other Notes
- Although it is stated that database is not necessary, a simple H2 database with simple JPA repositories are included for a complete solution.
- Project is constructed with spring-boot for simple configuration
- Java 8 features are included where applicable (for instance, Stream usage in RepositoryService)
- In total it took about 6-7 hours to complete, including tests, comments and README documentation. (Sorry for spending more than you expect)
- For simplicity of the API tests, only the HTTP status reponses are checked, JSON contents are not checked.