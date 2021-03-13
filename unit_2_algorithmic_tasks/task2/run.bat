@echo off
@echo COMPILING TASK 2...
call javac -sourcepath ./src -d out src/ua/com/alevel/Main.java
@echo.
@echo CHANGING ENCODING...
call chcp 65001
@echo.
@echo RUNNING...
call java -cp "./out" ua.com.alevel.Main
@echo.