@echo off 
@echo RUNNING MAVEN
call mvn clean install
@echo RUNNING MAVEN APP
call mvn exec:java
