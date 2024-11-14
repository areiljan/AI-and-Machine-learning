# Naive Bayes implementation
This project implements a Naive Bayes classifier to predict the topic of BBC news articles.

**Text Preprocessing**:
- All words are converted to lowercase.
- Words shorter than or equal to 3 characters are removed.
- Certain punctuation marks are removed.

**Model Training**:
- Calculated prior probabilities P(c) for each topic.
- Counted word occurrences in each topic to compute Nw,c.
- Computed total word counts Nc for each topic.
- Determined the vocabulary size |V|.

**Prediction**:
- For each test article, computed log probabilities log(h(c)) for each topic.
- Used Laplace smoothing (a.k.a. +1 smoothing) to handle unseen words.
- Predicted the topic with the highest log probability.

The classifier got accuracy of 96% on the test set, which is quite good in my opinion.
V ACTUAL      tech politics business entertainment sport
tech            20        1        0             0     0
politics         0       20        1             1     0
business         0        0       22             0     0
entertainment    0        0        0            18     0
sport            0        1        0             0    16 

Here is the confusion matrix showing the expected and actual results. 
If the actual and V term match, that means that the guess was right.
