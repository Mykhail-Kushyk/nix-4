@echo off
call ant clean
call ant compile
call ant jar
@echo RUNNING ANT APP
call ant run