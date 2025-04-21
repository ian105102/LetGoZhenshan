package com.example.letgozhenshan.MemoryGame;

import java.util.List;

public interface MemoryGameListener {
    void onUpdateUI(List<Card> cards); // 用來通知 UI 更新

    void onMatchSuccess(Card c1, Card c2);

    void onMatchFail(Card c1, Card c2);

    void onGameEnd();
}
