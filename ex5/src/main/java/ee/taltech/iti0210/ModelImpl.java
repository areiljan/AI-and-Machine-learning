package main.java.ee.taltech.iti0210;

import java.util.*;

/**
 * Your implementation goes here.
 */
public class ModelImpl implements Model
{
	private Map<String, Map<String, Integer>> wordCountsPerClass = new HashMap<>();
    private Map<String, Integer> totalWordsPerClass = new HashMap<>();
    private Map<String, Integer> articleCountsPerClass = new HashMap<>();
    private Set<String> vocabulary = new HashSet<>();
    private int totalArticles = 0;
    private Map<String, Double> logPriorPerClass = new HashMap<>();
    private int vocabularySize = 0;
	@Override
	public void train(TokenizedFile trainingSet) {
		for (TokenizedEntry entry : trainingSet.getEntries()) {
			String topic = entry.getTopic();
			TokenizedText text = entry.getText();
			List<String> tokens = text.getTokens();

			articleCountsPerClass.put(topic, articleCountsPerClass.getOrDefault(topic, 0) + 1);
			totalArticles += 1;

			Map<String, Integer> wordCounts = wordCountsPerClass.getOrDefault(topic, new HashMap<>());

			for (String token : tokens) {
				wordCounts.put(token, wordCounts.getOrDefault(token, 0) + 1);

				totalWordsPerClass.put(topic, totalWordsPerClass.getOrDefault(topic, 0) + 1);

				vocabulary.add(token);
			}
			wordCountsPerClass.put(topic, wordCounts);
		}

		vocabularySize = vocabulary.size();

		for (String topic : articleCountsPerClass.keySet()) {
			double prior = (double) articleCountsPerClass.get(topic) / totalArticles;
			logPriorPerClass.put(topic, Math.log(prior));
		}
	}

	// this function uses +1 smoothing also called laplace smoothing
	@Override
	public String predict(TokenizedText query) {
		List<String> tokens = query.getTokens();
		String bestClass = null;
		double bestLogProb = Double.NEGATIVE_INFINITY;

		for (String topic : articleCountsPerClass.keySet()) {
			double logProb = logPriorPerClass.get(topic);
			Map<String, Integer> wordCounts = wordCountsPerClass.get(topic);
			int totalWordsInClass = totalWordsPerClass.get(topic);

			for (String token : tokens) {
				int count = wordCounts.getOrDefault(token, 0);
				double probability = (count + 1.0) / (totalWordsInClass + vocabularySize);
				logProb += Math.log(probability);
			}

			if (logProb > bestLogProb) {
				bestLogProb = logProb;
				bestClass = topic;
			}
		}

		return bestClass;
	}
}
