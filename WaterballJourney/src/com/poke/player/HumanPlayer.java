package com.poke.player;

public class HumanPlayer extends Player {
    @Override
    public void naming(int order) {
        setName("Human-" + order);
    }
}
