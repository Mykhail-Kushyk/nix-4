@echo off
call mvn test
call mvn clean install
@echo RUNNING APPLICATION...
call java -jar target\unit_5_oop-1.0-SNAPSHOT-jar-with-dependencies.jar
call pause