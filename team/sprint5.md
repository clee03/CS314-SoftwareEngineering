# Sprint 5 - T17 - TBD

## Goal

### Reliable first release with clean code!

## Definition of Done

* Ready for demo / customer release.
* Sprint Review and Restrospectives completed.
* Product Increment release `v5.0` created on GitHub with appropriate version number and name, a description based on the template, and an executable JAR file for the demo.
* Version in pom.xml should be `<version>5.0.0</version>`.
* Unit tests for all new features and public methods at a minimum.
* Coverage at least 50% overall and for each class.
* Clean continuous integration build/test on master branch.

## Policies

* Tests and Javadoc are written before/with code.  
* All pull requests include tests for the added or modified code.
* Master is never broken.  If broken, it is fixed immediately.
* Always check for new changes in master to resolve merge conflicts locally before committing them.
* All changes are built and tested before they are committed.
* Continuous integration always builds and tests successfully.
* All commits with more than 1 line of change include a task/issue number.
* All Java dependencies in pom.xml.


## Metrics

Statistic | Planned | Completed
--- | ---: | ---:
Tasks |  18   | 15 
Story Points |  22  | 20


Statistic | Start | End
--- | ---: | ---:
Overall Test Coverage | 75% classes, 55% lines covered | 91% classes, 60% lines covered 
Smells | 31 | 21 
Duplication | 24 | 17 
Technical Debt Ratio | 18.7% | 12.8% 

## Plan

Epics planned for this release.

* #126 - Select a starting location
* #212 - Online map with pan and zoom
* #213 - Clean code
* #216 - Test coverage

## Daily Scrums

Date | Tasks done now | Tasks done next | Impediments | Coverage | Smells | Duplication | Technical Debt Ratio
:--- | :--- | :--- | :--- | ---: | ---: | ---: | ---:
11/27 | #224 | #213 | None | 55% | 31 | 24 | 18.6%
11/29 | #213 | #230 | None | 55% | 31 | 24 | 18.1%
12/04 | #212  | #234, #186, #198  | None | 55% | 31 | 24 | 18.1%
12/05 | #233, #234  | #222, #221, #208  | None | 57% | 18 | 17 | 14.1%
 

## Review

#### Completed user stories (epics) in Sprint Backlog 
* #212 - Online map with pan and zoom
* #213 - Clean code
* #216 - Test coverage

#### Incomplete user stories / epics in Sprint Backlog 
* #126 - Select a starting location: We focused on the other epics and issues associated with those epics and did not have time to complete this one. 

#### What went well
* The team as a whole did a good job completing issues and working together/talking through how to solve them. 

#### Problems encountered and resolutions
* We struggled with sickness and one of the team members laptops breaking this sprint.  Three opt was also very difficult to get working but we were able to finally analyze the data and get it working. 

## Retrospective

Topic | Teamwork | Process | Tools
:--- | :--- | :--- | :---
What we will change this time | spend more time working as a group instead of seperately | We will be more active on Zenhub, checking for issues to complete | We will attempt to use the burndown chart more effectively for pacing 
What we did well | There was a lot of positive communication and good distribution of labour | We all knew where we were in the process and we all worked to help eachother | We worked efficiently on github, not leaving pull requests open for too long and paid a lot of attention to slack
What we need to work on | We need to work on our pair programming skills to better solve complex problems | We need to add more javadocs to our methods | Groupmember needed to install stack on new phone
What we will change next time | Working with others more frequently | Add more javadocs for our methods for easier readability | We will assist getting slack installed and pay closer attention to the needs of our group members
