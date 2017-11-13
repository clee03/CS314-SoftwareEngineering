# Sprint 4 - T17 - TBD

## Goal

### Worldwide trips!

## Definition of Done

* Ready for the demo.
* Sprint Review and Restrospectives completed.
* Product Increment release `v4.0` created on GitHub with appropriate version number and name, a description based on the template, and an executable JAR file for the demo.
* Version in pom.xml should be `<version>4.0.0</version>`.
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

## Plan 

Epics in the Sprint Backlog: 5

Total planned tasks in the Sprint Backlog: 5 

Total story points in the Sprint Backlog: 16

## Daily Scrums

Date | Tasks done this time | Tasks done next time | Impediments | Confidence
:--- | :--- | :--- | :--- | :---
10/25 | Scheduled Team Meeting for Sprint Planning | Start working on sprint 4 | *None* | *High*
10/26 | Created tasks to complete which are associated with Epics and did planning poker to decide on estimates for the tasks | 314 Exam | *High* | 
10/30 | Talked about what code we wanted to use for code inspection | Individual code inspections | *None* | *High*
11/1 | Went over each code inspection and discussed what we wanted changed | | *None* | *High*
11/3 | Talked about SQL class and using a Brewery constructor that takes a hash map, 3 Opt, and completion date | Most Epics in backlog should be done | *None* | *High*
11/6 | Talked about 3opt | 3opt | *none* | *High*
11/8 | #170, #164, #174 | Complete backlog | Save and Load | *High*

 

## Review

#### Completed user stories (epics) in Sprint Backlog 
* #172: User has control over planning their trip
* #123: Web server now delivers content to the user
* #177: User can now select opt levels
* #170: User can now plan trips and see them on a worldwide map
* #140: User can plan their entire trip from searches
* #139: User can select destinations
* #127: User can save and load trips

backlog 
* All backloged user stories are done.

#### What went well
* We started earlier and got more done with less stress.
* Collaboration was on point.

#### Problems encountered and resolutions
* Constructor wasn't scaling, rewrote constructor to allow more data.
* Tables had wierd styling caused by changes in Chrome63, reverted to older ways of styling

## Retrospective

Topic | Teamwork | Process | Tools
:--- | :--- | :--- | :---
What we will change this time | Have clear definitions of interaction between methods/components | Start early enough to debug critcal issues | Make better ordering of React component files
What we did well | Communicated how pieces should go together before starting | Started earlier | Code climate
What we need to work on |  | Documenting sprint progress in sprint.md |
What we will change next time |  |  | 
