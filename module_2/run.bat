@echo off
call mvn test
call mvn clean install
call java -jar target\module_2-1.0-SNAPSHOT-jar-with-dependencies.jar
call pause