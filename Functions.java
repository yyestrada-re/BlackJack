import java.util.ArrayList;

//the following class is written in the perspective of the user. Did the user win or lose?
public class Functions extends Cards {
    private Cards cards;

    public static double getWager(double total) {
	System.out.print("How much would you like to bet?: ");
	double bet = run.nextInt();
	if (bet > total) {
	    System.out.println("Please bet within the total's constraints!");
	} else {
	    System.out.println("You bet $" + bet);
	}
	return bet;
    }

    public static double getRemaining(boolean status, double total, double bet) {
	if (status) { // won
	    total += bet;
	} else {
	    total -= bet;
	}
	return bet;
    }

    // method to get the total USER sum - to be used in other methods
    public static int getUserSum(ArrayList<Integer> user) {
	int sum = 0;
	for (int element : user) {
	    // System.out.println("The following array: " + element);
	    sum += element;
	}
	// System.out.println("Sum: " + sum);
	return sum;
    }

    // method to get the total DEALER sum - to be used in other methods
    public static int getDealerSum(ArrayList<Integer> user) {
	int sum = 0;
	for (int element : user) {
	    sum += element;
	}
	return sum;
    }

    public String hit() {
	String cardThree = cards.userDrawCard();
	return cardThree;
    }

    public void getTallies(int userWins, int dealerWins, String userName) {
	String winning = userName;
	if (dealerWins >= userWins) { // dealer wins if tied
	    winning = "Dealer";
	}
	System.out.println("\n" + userName + "'s wins: " + userWins + "\nDealer's wins: " + dealerWins + ". \n"
		+ winning + " is currently winning.");
    }

    // method that checks to see if either the user or the dealer won. Might need
    // some modification as you go on. FIRST TWO CARDS DRAWN DETERMINE WHETHER THE
    // USER WON OR NOT!
    public static String getStatus(int userSum, int dealerSum) {
	String status = "continue";
	// did the user bust?
	if (userSum > 21) {
	    status = "busted";
	}
	if (dealerSum > 21) {
	    status = "won";
	}
	if (userSum <= 21 && userSum > dealerSum) {
	    status = "exceeded dealer";
	}
	return status;
    }
}
