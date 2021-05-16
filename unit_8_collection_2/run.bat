@echo off
call mvn clean install
@echo RUNNING APPLICATION...
call java -jar target\unit_8_collection_2-1.0-SNAPSHOT-jar-with-dependencies.jar
call pause