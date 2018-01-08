package dt.core;

import java.util.*;

public class DecisionTreeLearner extends AbstractDecisionTreeLearner {

	public DecisionTreeLearner(Problem problem) {
		super(problem);
	}

	/**
	 * Implementation of the decision-tree learning algorithm in AIMA Fig 18.5, which is based on ID3.
	 */
	@Override
	protected DecisionTree learn(Set<Example> examples, List<Variable> attributes, Set<Example> parent_examples) {
		if(examples.size() == 0)
			return new DecisionTree(this.pluralityValue(parent_examples));
		if(this.uniqueOutputValue(examples) != null)
			return new DecisionTree(this.uniqueOutputValue(examples));
		if(attributes.isEmpty())
			return new DecisionTree(this.pluralityValue(examples));
		Variable A = this.mostImportantVariable(attributes, examples);
		DecisionTree tree = new DecisionTree(A);
		for(String vk : A.domain) {
			Set<Example> exs = examplesWithValueForAttribute(examples,A,vk);
			attributes.remove(A);
			DecisionTree subtree = learn(exs,attributes,examples);
			tree.children.add(subtree);
		}
		return tree;
	}

	@Override
	protected String pluralityValue(Set<Example> examples) {
		ArrayList<String> outputValues = new ArrayList<String>();
		for(Example e : examples) {
			outputValues.add(e.getOutputValue());
		}
		HashMap<String, Integer> hash = new HashMap<String, Integer>();
		for (String s : outputValues) {
			if (hash.containsKey(s)) {
				hash.put(s, hash.get(s).intValue() + 1);
			} else {
				hash.put(s, 1);
			}
		}
		String max = hash.keySet().iterator().next();
		for (String key : hash.keySet()) {
			if (hash.get(key) > hash.get(max)) {
				max = key;
			}
		}
		return max;
	}

	@Override
	protected String uniqueOutputValue(Set<Example> examples) {
		ArrayList<Example> exs = new ArrayList<Example>(examples);
		String output = exs.get(0).getOutputValue();
		for(Example e : exs) {
			if(!e.getOutputValue().equals(output))
				return null;
		}
 		return output;
	}

	@Override
	protected Set<Example> examplesWithValueForAttribute(Set<Example> examples, Variable a, String vk) {
		Set<Example> exs = new HashSet<Example>();
		for(Example e : examples) {
			if(e.inputValues.get(a).equals(vk))
				exs.add(e);
		}
		return exs;
	}

	@Override
	protected int countExamplesWithValueForAttribute(Set<Example> examples, Variable a, String vk) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int countExamplesWithValueForOutput(Set<Example> examples, String vk) {
		// TODO Auto-generated method stub
		return 0;
	}

}