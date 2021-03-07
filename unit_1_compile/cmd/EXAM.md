# Open cmd here to run program and paste this
javac -cp "libs/commons-lang3-3.12.0.jar;libs/commons-math3-3.6.1.jar" -sourcepath ./src -d build src/ua/com/alevel/Main.java

java -cp "./build;libs/commons-lang3-3.12.0.jar;libs/commons-math3-3.6.1.jar" ua.com.alevel.Main
