README

Contact Information:

Name: Viet Duong
Course: CSC 242 SPRING 2017
Assignment: Project 04

**Important Notes for Graders

1. I chose the Decision Tree Learning topic for this project, which includes the implementation of the algorithm and the tests.

2. I also added a class LossFunctionAnalysis.java in order to output the data for the plots of learning curve, 1/0 loss function, empirical loss function and entropy function. This outputs 4 .txt files with the data similar to the ones that I used for the plots in the write-up.

**Files included
	Files provided by Professor Ferguson:
		All files in src/dt/
	Files created by me:
		IrisDiscreteProblem.java
		LossFunctionAnalysis.java

**How to compile and run:

1. Change directory to the project04-vduong/src folder in terminal.

2. To compile the code, use the following commands
	javac dt/core/*.java
	javac dt/example/*.java
	javac dt/util/*.java

3. To run WillWaitProblem.java
	java dt.example.WillWaitProblem dt/example/WillWait-data.txt

4. To run DiscreteIrisProblem.java
	java dt.example.DiscreteIrisProblem dt/example/iris.data.discrete.txt

5. To run LossFunctionAnalysis.java and output the data text files
	java dt.example.LossFunctionAnalysis dt/example/iris.data.discrete.txt