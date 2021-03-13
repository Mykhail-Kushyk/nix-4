@echo off
IF NOT EXIST out (
md out
)
@echo COMPILING TASK 1...
@echo.
call javac -sourcepath ./src -d out src/ua/com/alevel/Main.java
@echo.
@echo RUNNING...
call java -cp "./out" ua.com.alevel.Main
@echo.