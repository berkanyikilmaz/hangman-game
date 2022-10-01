import java.util.*;

public class HangmanApp {
    
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String playerGuess; 

        System.out.println("Welcome to the Best Hangman Game You've Ever Seen!");
        System.out.println("You have maximum 6 letter tries, if you exceed the limit you will lose!");
        System.out.println("Fasten your seatbelts, game is about to start!");

        Hangman current = new Hangman();
        //System.out.println(current.secretWord);

        while ( !current.isGameOver() ){
            
            int occurenceOfLetter = 0;

            System.out.print("Let's try a letter: ");
            playerGuess = input.nextLine().toUpperCase();
            while ( current.getUsedLetters().indexOf(playerGuess) >= 0) {
                System.out.print("You have tried this letter, please try another: ");
                playerGuess = input.nextLine().toUpperCase();
            }
            System.out.println();
            occurenceOfLetter = current.tryThis(playerGuess);
            current.updateUsedAndUnusedLetters(playerGuess);
            if  ( occurenceOfLetter == 0 ){
                System.out.println("Your letter is not in the secret word, please try again");
                System.out.println( "Your Secret Word: " + current.getKnownSoFar() );
                System.out.println("The letters you used: " + current.getUsedLetters());
                System.out.println(current.displayTheHangman());
                System.out.println();
            }

            else if ( occurenceOfLetter > 0 ){
                System.out.println( "Your guess is corret!");
                current.updateKnownSoFar(playerGuess);
                System.out.println( "Your Secret Word: " + current.getKnownSoFar() );
                System.out.println("The letters you used: " + current.getUsedLetters());
                System.out.println(current.displayTheHangman());
                System.out.println();
            }
        }

        
        if (current.hasLost()) System.out.println("You have LOST! Your word was: " + current.getSecretWord() + "\n" + current.displayTheHangman());

        else  System.out.println("You have WON! CONGRATULATIONS!");
        System.out.println("Game is over! Thank you for playing our game! ");
        
    }
}
