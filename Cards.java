import java.util.Scanner;

public class Cards {
    public static String[] face = { "King", "Queen", "Jack", "Ace", "Spade", "Heart", "Diamond", "Club" };
    public static Scanner run = new Scanner(System.in);

//generates a random index from which to extract from the face ArrayList
    public static int drawAtIndex() {
	int index = (int) (Math.random() * 8) + 1;
	return index;
    }

    // user draws a card
    public static String userDrawCard() {
	String card = face[drawAtIndex() - 1];
	return card;
    }

    public int userDrawValue(String card) {
	int value = 0;
	boolean otherCards = false;
	if (card.equals("King") || card.equals("Queen") || card.equals("Jack")) {
	    value = 10;
	    otherCards = true;
	}
	if (card.equals("Ace")) {
	    System.out.println("You drew an Ace. Do you want your Ace to be 1 or 11?");
	    value = run.nextInt();
	    if (value != 1 && value != 11) {
		System.out.println("Please choose either one of the specified values"); // add total sum
		value = run.nextInt();
	    }
	    otherCards = true;
	}
	if (!otherCards) {
	    value = (int) (Math.random() * 9) + 2;
	    // System.out.println("Card: " + card + " Value " + value);
	}
	return value;
    }

    // dealer draws a card
    public String dealerDrawCard() {
	String card = face[drawAtIndex() - 1];
	return card;
    }

    public int dealerDrawValue(String card) {
	int value = 0;
	boolean otherCards = false;
	if (card.equals("King") || card.equals("Queen") || card.equals("Jack")) {
	    value = 10;
	    otherCards = true;
	}
	if (card.equals("Ace")) {
	    int randomChoose = (int) (Math.random());
	    int[] array = { 1, 11 };
	    value = array[randomChoose];
	    otherCards = true;
	}
	if (!otherCards) {
	    value = (int) (Math.random() * 9) + 2;
	}
	return value;
    }

}
