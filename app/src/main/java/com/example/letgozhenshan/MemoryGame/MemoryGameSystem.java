package com.example.letgozhenshan.MemoryGame;

import android.os.Handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryGameSystem {



    private List<Card> cards;
    private Card firstSelectedCard = null;
    private MemoryGameListener listener;
    private int pairCount;
    private Boolean onSelect = false;

    public MemoryGameSystem(int pairCount, MemoryGameListener listener) {
        this.pairCount = pairCount;
        this.listener = listener;
        initCards();
    }

    public List<Card> getCards() {
        return cards;
    }

    private void initCards() {
        cards = new ArrayList<>();
        int id = 0;
        for (int i = 0; i < pairCount; i++) {
            cards.add(new Card(id++, i));
            cards.add(new Card(id++, i));
        }
        Collections.shuffle(cards);
    }

    public void startPreview(int seconds) {
        for (Card card : cards) {
            card.isFlipped = true;
        }
        listener.onUpdateUI(cards);

        new Handler().postDelayed(() -> {
            for (Card card : cards) {
                card.isFlipped = false;
            }
            listener.onUpdateUI(cards);
        }, seconds * 1000L);
    }

    public void selectCard(int cardId) {
        if(onSelect)return;

        Card selectedCard = null;
        for (Card c : cards) {
            if (c.id == cardId) {
                selectedCard = c;
                break;
            }
        }
        if (selectedCard == null || selectedCard.isFlipped || selectedCard.isMatched) return;

        selectedCard.isFlipped = true;
        listener.onUpdateUI(cards);

        if (firstSelectedCard == null) {
            firstSelectedCard = selectedCard;
        } else {
            Card c1 = firstSelectedCard;
            Card c2 = selectedCard;

            if (c1.type == c2.type) {
                c1.isMatched = true;
                c2.isMatched = true;
                listener.onMatchSuccess(c1, c2);
                firstSelectedCard = null;

                if (isGameFinished()) {
                    listener.onGameEnd();
                }

            } else {
                onSelect = true;
                listener.onMatchFail(c1, c2);

                new Handler().postDelayed(() -> {
                    c1.isFlipped = false;
                    c2.isFlipped = false;
                    onSelect = false;
                    listener.onUpdateUI(cards);
                }, 1000);
                firstSelectedCard = null;
            }
        }
    }

    private boolean isGameFinished() {
        for (Card c : cards) {
            if (!c.isMatched) return false;
        }
        return true;
    }
}
