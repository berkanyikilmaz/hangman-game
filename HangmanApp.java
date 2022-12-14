import java.util.*;

public class HangmanApp {
    
    public static void main(String[] args) {

        Locale.setDefault(new Locale("en"));

        Scanner input = new Scanner(System.in);
        String playerGuess; 
        int predictionChoice = 0;

        System.out.println("Welcome to the Best Hangman Game You've Ever Seen!");
        System.out.println("Game modes you can choose are \n1- Word Guessing Game\n2- Sentence Guessing Game");
        System.out.print("Please choose which game mode you want to play: ");
        int gameMode = input.nextInt();

        while (gameMode != 1 && gameMode != 2) {
            System.out.print("Invalid game mode, please enter a valid game mode: ");
            gameMode = input.nextInt();
        }

        Hangman current = new Hangman(gameMode);

        System.out.println();
        input.nextLine();
        System.out.println("You have maximum " + current.getMaxAllowedIncorrectTries() + " letter tries, if you exceed the limit you will lose!");
        System.out.println("Fasten your seatbelts, game is about to start!");

        //System.out.println(current.secretWord);

        while ( !current.isGameOver() ){
            
            if (predictionChoice != 1) {

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
                }

                else if ( occurenceOfLetter > 0 ){
                    System.out.println( "Your guess is correct!");
                    current.updateKnownSoFar(playerGuess);
                }

                System.out.println( "Your Secret Word: " + current.getKnownSoFar() );
                System.out.println("The letters you used: " + current.getUsedLetters());
                System.out.println(current.displayTheHangman());
                if (!current.isGameOver()){                
                    System.out.print("If you want to predict the word enter 1 otherwise 0: ");
                    predictionChoice = input.nextInt();
                    System.out.println();
                    input.nextLine();
                }
            }

            else {
                System.out.println("Guess the secret word: ");
                playerGuess = input.nextLine();
                if(current.predictWord(playerGuess)){
                    System.out.println("You Are Correct! ");
                }

                else {
                    System.out.println("Wrong Guess!");
                    System.out.println( "Your Secret Word: " + current.getKnownSoFar() );
                    System.out.println("The letters you used: " + current.getUsedLetters());
                    System.out.println(current.displayTheHangman());
                    if( !current.isGameOver() ){
                        System.out.print("If you want to predict the word enter 1 otherwise 0: ");
                        predictionChoice = input.nextInt();
                    }
                    System.out.println();
                    input.nextLine();
                } 

            }

        }

        if ( current.hasLost() ) System.out.println("You have LOST! Your word was: " + current.getSecretWord() + "\n" + current.displayTheHangman());

        else  System.out.println("You have WON! CONGRATULATIONS!");
        System.out.println("Game is over! Thank you for playing our game! ");
        
        input.close();
    }
}
