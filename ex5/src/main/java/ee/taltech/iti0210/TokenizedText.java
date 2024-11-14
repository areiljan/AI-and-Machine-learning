package main.java.ee.taltech.iti0210;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TokenizedText
{
	private List<String> tokens = new ArrayList<>();
	private static final List<String> FILTER_OUT = Arrays.asList(
		"\"",
		"\\.",
		","
	);

	public TokenizedText(String text)
	{
		for (String f: FILTER_OUT)
		{
			text = text.replaceAll(f, "");
		}

		for (String token: text.split("\\s+"))
		{
			token = token.strip().toLowerCase();
			if (token.length() > 3) {
				this.tokens.add(token);
			}
		}
	}

	private String processToken(String token)
	{
		return token.strip();
	}

	public List<String> getTokens()
	{
		return Collections.unmodifiableList(this.tokens);
	}
}
