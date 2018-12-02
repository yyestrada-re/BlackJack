import java.util.ArrayList;
import java.util.Scanner;

public class RunBlackJack {
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		double money = 1000.00;
		boolean win = true;
		Scanner run = new Scanner(System.in);
		boolean cont = true;
		int round = 0;
		int i = -1;
		Functions functions = new Functions();
		Dealer dealer = new Dealer();
		Cards cards = new Cards();
		// GIU gui = new GUI();
		ArrayList<Integer> user = new ArrayList<Integer>();
		ArrayList<Integer> bot = new ArrayList<Integer>();
		ArrayList<Integer> roundNum = new ArrayList<Integer>();
		System.out.println("Welcome to BlackJack!");
		System.out.println(
				"\n\t\t\t\t\t\t\t\tInstructions: \n\tBlackjack is played with a 52-card deck. Face cards, such as Queens, Kings, and Jacks are worth 10 points. \n\tSuit cards have their pip value between 2-9. Aces can either be 1 or 11 according to the holder. \n\tThere are 3 ways to win: the dealer busts, or exceeds 21, or you remain within the game's constraints and exceed the dealer's play and vice-versa. Good luck! :) ");
		System.out.print("\nEnter your name: ");
		String name = run.nextLine();
		System.out.print("Your total is currently $1000\n");
		int userWins = 0, dealerWins = 0;
		// round numbers
		while (money > 0 && cont) { // add money and tell whether or not the dealer wants to hit or stay
			System.out.println("Total Money: " + money);
			win = true;
			user.clear();
			bot.clear();
			round++;
			i++;
			roundNum.add(round);
			System.out.println("\nROUND: " + roundNum.get(i));
			double bet = functions.getWager(money);
			String cardOne = cards.userDrawCard();
			user.add(cards.userDrawValue(cardOne));
			String cardTwo = cards.userDrawCard();
			user.add(cards.userDrawValue(cardTwo));
			System.out.println("\nYou drew a " + cardOne + " and a " + cardTwo);
			System.out.println("Your card's total is " + functions.getUserSum(user));
			// System.out.println("Would you like to double?" );
			if (functions.getUserSum(user) > 21) {
				System.out.println("You exceeded 21. Dealer wins");
				win = false;
				dealerWins++;
				money -= bet;
				System.out.println("You have $" + money + " left.");
				// System.out.println("You have $" + functions.getRemaining(win, money, bet) + "
				// remaining"); // boolean,
				// total,
				// wager
				functions.getTallies(userWins, dealerWins, name);
				// play again?
				System.out.print("Would you like to play again? Enter Yes or No: ");
				String decision = run.nextLine();
				if (decision.toLowerCase().equals("yes")) {
					continue;
				} else {
					cont = false;
					break;
				}
				// money -= bet;
			}
			System.out.println("\nDealer's turn: ");
			String dealerOne = cards.dealerDrawCard();
			bot.add(cards.dealerDrawValue(dealerOne));
			System.out.println("The dealer has a " + dealerOne + " and a hidden card. His total is hidden too");
			System.out.println("Would you like to Hit or Stay?");
			String choice = run.nextLine();
			if ((choice.toLowerCase()).equals("hit")) {
				String cardThree = functions.hit();
				user.add(cards.userDrawValue(cardThree));
				System.out.println("You drew a " + cardThree + ". Your total is now " + functions.getUserSum(user));
				if (functions.getUserSum(user) > 21) {
					win = false;
					dealerWins++;
					System.out.println("You exceeded 21. Dealer wins.");
					money -= bet;
					System.out.println("You have $" + money + " left.");
					functions.getTallies(userWins, dealerWins, name);
					// play again?
					System.out.print("Would you like to play again? Enter Yes or No: ");
					String decision = run.nextLine();
					if (decision.toLowerCase().equals("yes")) {
						continue;
					} else {
						cont = false;
						break;
					}
				}
			}
			String dealerTwo = cards.dealerDrawCard();
			bot.add(cards.dealerDrawValue(dealerTwo));
			System.out.println("\nDealer's turn: ");
			try {// 2 seconds
				Thread.sleep(2000);
				System.out.println("\nDealer is looking at hand..");

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {// 3 seconds
				Thread.sleep(3000);
				System.out.println("Dealer is looking at hand...");

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(
					"The Dealer's hidden card was a " + dealerTwo + ". His total is " + functions.getDealerSum(bot));
			String status = functions.getStatus(functions.getUserSum(user), functions.getDealerSum(bot));
			if (status.equals("won")) {
				System.out.println("Dealer busted. " + name + " wins!");
				userWins++;
				money += bet;
				System.out.println("You have $" + money + " remaining");
				functions.getTallies(userWins, dealerWins, name);
			}
			if (status.equals("exceeded dealer")) { // potential option would have been for the Dealer to hit or stay
				System.out.println("\nYou're closer to 21 than the Dealer. " + name + " wins!");
				userWins++;
				money += bet;
				System.out.println("You have $" + money + " remaining");
				functions.getTallies(userWins, dealerWins, name);
			} else {
				System.out.println("\nDealer is closer to 21 than the user. Dealer wins.");
				dealerWins++;
				money -= bet;
				System.out.println("You have $" + money + " remaining");
				functions.getTallies(userWins, dealerWins, name);
				System.out.print("Would you like to play again? Enter Yes or No: ");
				String decision = run.nextLine();
				if (decision.toLowerCase().equals("yes")) {
					continue;
				} else {
					cont = false;
					break;
				}
			}
		}
	}
}
