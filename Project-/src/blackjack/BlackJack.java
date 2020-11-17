package blackjack;

import java.util.Scanner;

public class BlackJack {
    public static void main(String[] args) {
        System.out.println("Welcome");
        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffle();

        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();
        double playerMoney = 1000 - 00;

        Scanner userInput = new Scanner(System.in);

        while (playerMoney > 0) {
            System.out.println("You have $" + playerMoney + ", how much would you like to bet?");
            double playerBet = userInput.nextDouble();
            if (playerBet > playerMoney) {
                System.out.println("You cannot bet more than you have. Please Leave!");
            }

            boolean endRound = false;

            playerDeck.draw(playingDeck);
            playerDeck.draw(playingDeck);

            dealerDeck.draw(playingDeck);
            dealerDeck.draw(playingDeck);

            while(true){
                System.out.println("Your Hand");
                System.out.println(playerDeck.toString());
                System.out.println("Your hand is valued at: " + playerDeck.cardValue());
                System.out.println("Dealer Hand: " + dealerDeck.getCard(0).toString()+ "and [HIDDEN]");
                System.out.println("Would you like to (1) Hit or (2) Stand? ");
                int response = userInput.nextInt();

                if (response == 1){
                    playerDeck.draw(playingDeck);
                    System.out.println("You draw a:" + playerDeck.getCard(playerDeck.deckSize()-1).toString());
                    if (playerDeck.cardValue()>21){
                        System.out.println("Bust! Currently valued at: " + playerDeck.cardValue());
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    }
                }

                if (response == 2){
                    break;
                }
            }
            System.out.println("Dealer Cards: " + dealerDeck.toString());
            if (dealerDeck.cardValue() > playerDeck.cardValue()&& endRound == false){
                System.out.println("Dealer Wins!");
                playerMoney -= playerBet;
                endRound = true;
            }
            while ((dealerDeck.cardValue()< 17)&& endRound == false ){
                dealerDeck.draw(playingDeck);
                System.out.println("Dealer Draws:" + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
            }
            System.out.println("Dealer's Hand is valued at: " + dealerDeck.cardValue());
            if ((dealerDeck.cardValue() > 21)&& endRound == false){
                System.out.println("Dealer busts! You Win!");
                playerMoney += playerBet;
                endRound = true;
            }

            if ((playerDeck.cardValue() == dealerDeck.cardValue())&& endRound == false){
                System.out.println("Draw");
                endRound = true;
            }

            if ((playerDeck.cardValue() > dealerDeck.cardValue())&& endRound == false){
                System.out.println("YOU WIN!");
                playerMoney += playerBet;
                endRound = true;
            }

            playerDeck.moveAllToDeck(playingDeck);
            dealerDeck.moveAllToDeck(playingDeck);
            System.out.println("End of Hand.");
        }

        System.out.println("Game Over!");
    }

}