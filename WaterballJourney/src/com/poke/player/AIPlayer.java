package com.poke.player;

public class AIPlayer extends Player {
    @Override
    public void naming(int order) {
        setName("AI-"+order);
    }
}
