package com.example;

import java.util.Random;

public class JokeGenerator {
    private String[] jokes;
    private Random random;

    public JokeGenerator() {
        jokes = new String[3];
        jokes[0] = "Algorithm : Word used by programmers when.. they do not want to explain what they did";
        jokes[1] = "Q: Whats the object-oriented way to become wealthy? A: Inheritance.";
        jokes[2] = "Why do Java developers wear glasses? Because they can't C#";
        random = new Random();
    }

    public String[] getJokes() {
        return jokes;
    }

    public String getRandomJoke() {
        return jokes[random.nextInt(jokes.length)];
    }
}
