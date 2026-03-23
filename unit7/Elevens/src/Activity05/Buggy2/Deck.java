package src.Activity05.Buggy2;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private List<Card> cards;
    private int size;

    public Deck(final String[] array, final String[] array2, final int[] array3) {
        this.cards = new ArrayList<Card>();
        for (int i = 1; i < array.length; ++i) {
            for (int length = array2.length, j = 0; j < length; ++j) {
                this.cards.add(new Card(array[i], array2[j], array3[i]));
            }
        }
        this.shuffle();
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void shuffle() {
        for (int i = this.cards.size() - 1; i > 0; --i) {
            final int n = (int) (Math.random() * (i + 1)) + 0;
            final Card card = this.cards.get(i);
            this.cards.set(i, this.cards.get(n));
            this.cards.set(n, card);
        }
        this.size = this.cards.size();
    }

    public Card deal() {
        if (this.isEmpty()) {
            return null;
        }
        --this.size;
        return this.cards.get(this.size);
    }

    @Override
    public String toString() {
        String s = "size = " + this.size + "\nUndealt cards: \n";
        for (int i = this.size - 1; i >= 0; --i) {
            s += this.cards.get(i);
            if (i != 0) {
                s += ", ";
            }
            if ((this.size - i) % 2 == 0) {
                s += "\n";
            }
        }
        String s2 = s + "\nDealt cards: \n";
        for (int j = this.cards.size() - 1; j >= this.size; --j) {
            s2 += this.cards.get(j);
            if (j != this.size) {
                s2 += ", ";
            }
            if ((j - this.cards.size()) % 2 == 0) {
                s2 += "\n";
            }
        }
        return s2 + "\n";
    }
}