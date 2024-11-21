import nltk
import random
import tiktoken
from nltk.tokenize import sent_tokenize, word_tokenize
from collections import defaultdict, Counter

enc = tiktoken.get_encoding("gpt2")
nltk.download("punkt")
nltk.download("punkt_tab")
EOS = "<EOS>"

def tokenize(text):
    print('tokenizing')
    tokens = []
    for sentence in sent_tokenize(text):
        tokens.append(EOS)
        for token in word_tokenize(sentence):
            tokens.append(token)
    tokens.append(EOS)
    return tokens

def tokenize_subwords(text):
    return enc.encode(text)

def decode_tokens(tokens):
    return enc.decode(tokens)

def build_bigram(tokens):
    bigrams = defaultdict(lambda: Counter())

    for i in range(len(tokens) - 1):
        current_token = tokens[i]
        next_token = tokens[i + 1]
        bigrams[current_token][next_token] += 1

    return bigrams

def build_ngram(tokens, n):
    ngram = defaultdict(lambda: Counter())
    for i in range(len(tokens) - n + 1):
        context = tuple(tokens[i:i + n - 1])
        next_token = tokens[i + n - 1]
        ngram[context][next_token] += 1
    return ngram

def generate_bigram_text(max_length, bigrams):
    generated_text = []
    current_token = random.choice(list(bigrams.keys()))

    for token in range(max_length):
        generated_text.append(current_token)
        next_token_options = bigrams[current_token]
        if not next_token_options:
            break
        current_token = random.choices(
            list(next_token_options.keys()),
            weights=list(next_token_options.values())
        )[0]

    return " ".join(generated_text).replace('<EOS>', '').strip()

def generate_ngram_text(max_length, ngrams):
    current_context = random.choice(list(ngrams.keys()))
    generated_text = list(current_context)

    for i in range(max_length - len(current_context)):
        next_token_options = ngrams[current_context]

        if not next_token_options:
            break

        next_token = random.choices(
            list(next_token_options.keys()),
            weights=list(next_token_options.values())
        )[0]

        generated_text.append(next_token)

        current_context = tuple(generated_text[-len(current_context):])

    return " ".join(generated_text).replace('<EOS>', '').strip()


with open("bible.txt", encoding="UTF-8") as f:
    tokens = tokenize(f.read())
    N = len(tokens)
    print(N, "tokens")

print('Generating holy yap...')

bigram = build_bigram(tokens)
print('bigram: ' + generate_bigram_text(100, bigram))

trigram = build_ngram(tokens, 3)
print('trigram: ' + generate_ngram_text(100, trigram))

quadrgram = build_ngram(tokens, 4)
print('quadrgram: ' + generate_ngram_text(100, quadrgram))
