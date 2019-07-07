package com.mdzyuba.jokeslib;

public class Jokes {

    private final String[] jokes = {"Dad and a kid went to an aquarium and looking at the fish. " +
                                    "Dad explains to the kid that sardines like to swim together. " +
                                    "It is called a school of fish.\nThe kid is asking dad: " +
                                    "\"So, where is the teacher?\"",
            "Teacher: Hey Peter, what has happened to your home work? \n" +
            "Student: I ate it. \n" + "Teacher: Oh no, why? \n" +
            "Student: You said it is a piece of cake. \n"};

    public String tell(int index) {
        return jokes[index];
    }
}
