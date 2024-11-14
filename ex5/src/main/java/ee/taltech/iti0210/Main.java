package main.java.ee.taltech.iti0210;

public class Main
{
	public static void main(String[] args)
	{
		TokenizedFile trainSet = new TokenizedFile("bbc_train.csv");
		TokenizedFile testSet = new TokenizedFile("bbc_test.csv");

		Model m = new ModelImpl();

		m.train(trainSet);

		ConfusionMatrix confucius = new ConfusionMatrix();

		int correct = 0;
		int total = 0;

		for (TokenizedEntry entry: testSet.getEntries())
		{
			String correctAnswer = entry.getTopic();
			TokenizedText text = entry.getText();
			String prediction = m.predict(text);
			confucius.storePrediction(correctAnswer, prediction);

			if (prediction.equals(correctAnswer)) {
				correct++;
			}
			total++;
		}

		System.out.println(confucius);
		double accuracy = ((double) correct / total) * 100;
		System.out.printf("Accuracy: %.2f%%\n", accuracy);
	}
}
