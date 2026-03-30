package src.Activity01;

/**
 * This is a class that tests the Card class.
 */
public class CardTester {

    /**
     * The main method in this class checks the Card operations for consistency.
     * 
     * @param args is not used.
     */
    public static void main(String[] args) {
        Card card1 = new Card("Queen", "Clubs", 12) {};
        Card card2 = new Card("Ace", "Hearts", 2) {};
        Card card3 = new Card("Queen", "Clubs", 12) {};

        System.out.println(card1.toString());
        System.out.println(card1.matches(card3)); // true
        System.out.println(card3.matches(card2)); // false
        System.out.println(card2.pointValue()); // 2
    }
}
