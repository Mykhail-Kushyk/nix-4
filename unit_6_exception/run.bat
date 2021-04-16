@echo off
call mvn clean install
@echo RUNNING APPLICATION...
call java -jar target\unit_6_exception-1.0-SNAPSHOT-jar-with-dependencies.jar
call pause