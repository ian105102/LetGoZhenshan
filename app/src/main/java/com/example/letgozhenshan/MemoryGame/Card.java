package com.example.letgozhenshan.MemoryGame;

public class Card {
    public int id;
    public int type;
    public boolean isFlipped;
    public boolean isMatched;

    public Card(int id, int type) {
        this.id = id;
        this.type = type;
        this.isFlipped = false;
        this.isMatched = false;
    }
}
