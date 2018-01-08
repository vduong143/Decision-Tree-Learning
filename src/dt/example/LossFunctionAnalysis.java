package dt.example;

import java.io.*;
import java.util.*;

import dt.core.DecisionTree;
import dt.core.DecisionTreeLearner;
import dt.core.Example;
import dt.core.Problem;
import dt.util.ArraySet;

public class LossFunctionAnalysis {
	public static void main(String[] args) throws IOException {
		Problem problem = new DiscreteIrisProblem();
		problem.dump();
		Set<Example> examples = problem.readExamplesFromCSVFile(new File(args[0]));
		
		//write the computed 1/0 loss function, empirical loss, percentage correctly predicted
		//and the entropy of the set of training data in order to plot them against the size of
		//the training data in Excel
		
		BufferedWriter writer1 = new BufferedWriter(new FileWriter("1-0-loss.txt"));
		BufferedWriter writer2 = new BufferedWriter(new FileWriter("emperical-loss.txt"));
		BufferedWriter writer3 = new BufferedWriter(new FileWriter("pct-correct.txt"));
		BufferedWriter writer4 = new BufferedWriter(new FileWriter("entropy.txt"));
		for(int i = 1; i< examples.size(); i++) {
			ArraySet<Example> test = new ArraySet<Example>(examples);
			ArraySet<Example> train = new ArraySet<Example>();
			for(int j = 1; j <= i; j++) {
				Random rand = new Random();
				int randInt = rand.nextInt(test.size());
				train.elements.add(test.elements.remove(randInt));
			}
			DecisionTree tree = new DecisionTreeLearner(problem).learn(train);
			tree.dump();
			int loss = tree.test(test);
			writer1.write(loss+"\n");
			double eloss = (double)loss/(double)(test.size());
			writer2.write(eloss+"\n");
			double pct = 1-(double)loss/(double)test.size();
			writer3.write(pct+"\n");
			double entropy = new DecisionTreeLearner(problem).H(train);
			writer4.write(entropy+"\n");
		}
		writer1.close();
		writer2.close();
		writer3.close();
		writer4.close();
	}
}
