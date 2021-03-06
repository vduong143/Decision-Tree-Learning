package dt.example;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import dt.core.DecisionTree;
import dt.core.DecisionTreeLearner;
import dt.core.Domain;
import dt.core.Example;
import dt.core.Problem;
import dt.core.Variable;

public class DiscreteIrisProblem extends Problem{
	public DiscreteIrisProblem() {
		super();
		// Input variables
		Domain d = new Domain("S","MS","L","ML");
		this.inputs.add(new Variable("SepalLength", d));
		this.inputs.add(new Variable("SepalWidth", d));
		this.inputs.add(new Variable("PetalLength", d));
		this.inputs.add(new Variable("PetalWidth", d));
		// Output variable
		this.output = new Variable("Species", new Domain("Iris-setosa","Iris-versicolor","Iris-virginica"));
	}
	
	public static void main(String[] args) throws IOException {
		Problem problem = new DiscreteIrisProblem();
		problem.dump();
		Set<Example> examples = problem.readExamplesFromCSVFile(new File(args[0]));
		for (Example e : examples) {
			System.out.println(e);
		}
		DecisionTree tree = new DecisionTreeLearner(problem).learn(examples);
		tree.dump();
		tree.test(examples);
	}
}
