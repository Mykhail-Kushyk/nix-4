@echo off
call mvn clean install
call java -jar runner\target\runner-1.0-SNAPSHOT-jar-with-dependencies.jar
call pause