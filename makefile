# 206072290
# orilevy

compile: bin sources-compile
	javac -cp biuoop-1.4.jar:src -d bin @sources-compile.txt

jar:
	jar cfm ass7game.jar manifest.txt -C bin . -C resources .

run:
	java -cp biuoop-1.4.jar:resources:bin utilities.Ass7Game

bin:
	mkdir bin

sources-compile:
	find src -name "*.java" > sources-compile.txt