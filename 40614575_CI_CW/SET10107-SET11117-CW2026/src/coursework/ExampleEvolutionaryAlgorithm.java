package coursework;

import java.util.ArrayList;

import model.Fitness;
import model.Individual;
import model.NeuralNetwork;

/**
 * Evolutionary Algorithm using:
 * - Tournament selection (size 3)
 * - Uniform crossover
 * - Gaussian mutation
 * - Elitist replacement
 */
public class ExampleEvolutionaryAlgorithm extends NeuralNetwork {

	@Override
	public void run() {
		population = initialise();

		best = getBest();
		System.out.println("Best From Initialisation " + best);

		while (evaluations < Parameters.maxEvaluations) {

			Individual parent1 = select();
			Individual parent2 = select();

			ArrayList<Individual> children = reproduce(parent1, parent2);

			mutate(children);

			evaluateIndividuals(children);

			replace(children);

			best = getBest();

			outputStats();
		}

		saveNeuralNetwork();
	}

	private void evaluateIndividuals(ArrayList<Individual> individuals) {
		for (Individual individual : individuals) {
			individual.fitness = Fitness.evaluate(individual, this);
		}
	}

	private Individual getBest() {
		Individual currentBest = null;
		for (Individual individual : population) {
			if (currentBest == null || individual.fitness < currentBest.fitness) {
				currentBest = individual.copy();
			}
		}
		return currentBest;
	}

	private ArrayList<Individual> initialise() {
		ArrayList<Individual> pop = new ArrayList<>();
		for (int i = 0; i < Parameters.popSize; ++i) {
			Individual individual = new Individual();
			pop.add(individual);
		}
		evaluateIndividuals(pop);
		return pop;
	}

	/**
	 * Tournament selection, size 3
	 */
	private Individual select() {
		int tournamentSize = 3;
		Individual winner = null;

		for (int i = 0; i < tournamentSize; i++) {
			Individual candidate = population.get(
					Parameters.random.nextInt(population.size()));

			if (winner == null || candidate.fitness < winner.fitness) {
				winner = candidate;
			}
		}

		return winner.copy();
	}

	/**
	 * Uniform crossover
	 */
	private ArrayList<Individual> reproduce(Individual parent1, Individual parent2) {
		ArrayList<Individual> children = new ArrayList<>();

		Individual child1 = parent1.copy();
		Individual child2 = parent2.copy();

		for (int i = 0; i < parent1.chromosome.length; i++) {
			if (Parameters.random.nextBoolean()) {
				double temp = child1.chromosome[i];
				child1.chromosome[i] = child2.chromosome[i];
				child2.chromosome[i] = temp;
			}
		}

		children.add(child1);
		children.add(child2);

		return children;
	}

	/**
	 * Gaussian mutation
	 */
	private void mutate(ArrayList<Individual> individuals) {
		for (Individual individual : individuals) {
			for (int i = 0; i < individual.chromosome.length; i++) {
				if (Parameters.random.nextDouble() < Parameters.mutateRate) {
					individual.chromosome[i] +=
							Parameters.random.nextGaussian() * Parameters.mutateChange;

					if (individual.chromosome[i] < Parameters.minGene) {
						individual.chromosome[i] = Parameters.minGene;
					} else if (individual.chromosome[i] > Parameters.maxGene) {
						individual.chromosome[i] = Parameters.maxGene;
					}
				}
			}
		}
	}

	/**
	 * Elitist replacement:
	 * replace only if child is better than the current worst
	 */
	private void replace(ArrayList<Individual> children) {
		for (Individual child : children) {
			int worstIndex = getWorstIndex();
			if (child.fitness < population.get(worstIndex).fitness) {
				population.set(worstIndex, child);
			}
		}
	}

	private int getWorstIndex() {
		int idx = 0;
		Individual worst = population.get(0);

		for (int i = 1; i < population.size(); i++) {
			if (population.get(i).fitness > worst.fitness) {
				worst = population.get(i);
				idx = i;
			}
		}
		return idx;
	}

	@Override
	public double activationFunction(double x) {
		if (x < -20.0) {
			return -1.0;
		} else if (x > 20.0) {
			return 1.0;
		}
		return Math.tanh(x);
	}
}