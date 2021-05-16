@echo off
call mvn test
call mvn clean install
@echo RUNNING APPLICATION...
call java -jar target\unit_9_ionio-1.0-SNAPSHOT-jar-with-dependencies.jar
call pause