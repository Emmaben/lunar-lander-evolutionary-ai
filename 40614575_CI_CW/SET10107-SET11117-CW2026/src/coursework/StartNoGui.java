package coursework;

import model.Fitness;
import model.LunarParameters.DataSet;
import model.NeuralNetwork;

public class StartNoGui {

	public static void main(String[] args) {

		// Change the runs variable to the amount of times you'd like the code run!
		final int runs = 4;

		double[] trainingResults = new double[runs];
		double[] testResults = new double[runs];

		double trainingSum = 0.0;
		double testSum = 0.0;

		for (int i = 0; i < runs; i++) {

			// Best configuration found so far
			Parameters.maxEvaluations = 20000;
			Parameters.popSize = 100;
			Parameters.setHidden(15);
			Parameters.mutateRate = 0.05;
			Parameters.mutateChange = 0.1;

			// New random seed each run
			Parameters.seed = System.currentTimeMillis() + i;
			Parameters.random = new java.util.Random(Parameters.seed);

			// Train on training set
			Parameters.setDataSet(DataSet.Training);
			NeuralNetwork nn = new ExampleEvolutionaryAlgorithm();
			nn.run();

			double trainingFitness = Fitness.evaluate(nn);

			// Test on test set
			Parameters.setDataSet(DataSet.Test);
			double testFitness = Fitness.evaluate(nn);

			trainingResults[i] = trainingFitness;
			testResults[i] = testFitness;

			trainingSum += trainingFitness;
			testSum += testFitness;

			System.out.println("Run " + (i + 1));
			System.out.println("Training Fitness: " + trainingFitness);
			System.out.println("Test Fitness: " + testFitness);
			System.out.println("-----------------------------------");
		}

		double trainingMean = trainingSum / runs;
		double testMean = testSum / runs;

		System.out.println("FINAL RESULTS");
		System.out.println("===================================");
		for (int i = 0; i < runs; i++) {
			System.out.println(
				"Run " + (i + 1)
				+ " | Training: " + trainingResults[i]
				+ " | Test: " + testResults[i]
			);
		}

		System.out.println("===================================");
		System.out.println("Mean Training Fitness: " + trainingMean);
		System.out.println("Mean Test Fitness: " + testMean);
	}
}