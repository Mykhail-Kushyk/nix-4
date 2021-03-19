@echo off
call mvn clean install
@echo RUNNING CALCULATOR...
call java -jar program\target\program-1.0-SNAPSHOT-jar-with-dependencies.jar
call pause