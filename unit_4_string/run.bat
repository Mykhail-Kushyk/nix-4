@echo off
call mvn clean install
@echo RUNNING REVERSE...
call java -jar application\target\application-1.0-SNAPSHOT-jar-with-dependencies.jar
call pause