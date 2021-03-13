@echo off
@echo COMPILING TASK 3...
IF NOT EXIST out (
md out
)
call javac -sourcepath ./src -d out src/ua/com/alevel/Main.java
@echo.
@echo RUNNING...
call java -cp "./out" ua.com.alevel.Main
@echo.