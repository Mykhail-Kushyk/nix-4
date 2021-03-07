call cd cmd
@echo off
call runCmd.bat
call cd..
call cd ant
@echo off
call runAnt.bat
call cd..
call cd maven
@echo off
call runMaven.bat
call pause