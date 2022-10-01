import java.util.*;

public class Hangman {

    private StringBuffer secretWord;
    private StringBuffer allLetters;
    private StringBuffer unusedLetters;
    private StringBuffer usedLetters;
    private int numberOfIncorrectTries;
    private int maxAllowedIncorrectTries;
    private StringBuffer knownSoFar;

    private int gameMode;

    //Constructor
    public Hangman (int gameMode){
        this.numberOfIncorrectTries = 0;
        this.allLetters = new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        this.unusedLetters = new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        this.usedLetters = new StringBuffer("");
        this.knownSoFar = new StringBuffer();
        this.gameMode = gameMode;

        maxAllowedIncorrectTries = 7;
        
        chooseSecretWord();
        hideSecretWord(this.secretWord);
    }


    public String getAllLetters() {
        return this.allLetters.toString();
    }

    public String getUsedLetters() {
        return this.usedLetters.toString();        
    }

    public String getUnusedLetters() {
        return this.unusedLetters.toString();
    }

    public int getNumOfIncorrectTries() {
        return this.numberOfIncorrectTries;
    }

    public int getMaxAllowedIncorrectTries() {
        return this.maxAllowedIncorrectTries;        
    }

    public String getKnownSoFar() {
        return this.knownSoFar.toString();
    }

    public StringBuffer getSecretWord(){
        return this.secretWord;
    }

    //Method to hide the secretWord.
    public void hideSecretWord( StringBuffer secretWord ){
        for ( int i = 0; i < secretWord.length(); i++ ){
            if (secretWord.charAt(i) == ' ') {
                this.knownSoFar.replace(i, i+1, " ");
            }
            else {
                this.knownSoFar.replace(i, i+1, "_");
            }
        }
    }

    //Method to return the number of occurences of a letter in secretWord
    public int tryThis ( String letter ) {
        int result = 0;
        for ( int i = 0; i < this.secretWord.length(); i++ ){
            if( Character.toString(this.secretWord.charAt(i)).equals(letter) ) {
                result++;
            }
        }
        if (result == 0) this.numberOfIncorrectTries++;
        return result;
    }

    public boolean predictWord( String playerGuess ) {
        boolean isTrue = true;
        
        if (playerGuess.toUpperCase().equals(this.getSecretWord().toString())) {
            this.knownSoFar = this.secretWord;
        }
        else {
            this.numberOfIncorrectTries++;
            isTrue = false;
        }

        return isTrue;
    }

    //Method to decide if the game is over
    public boolean isGameOver(){
        if( hasLost() || hasWon()) return true;
        else return false;
    }

    //Method see if the player has lost or not.
    public boolean hasLost(){
        if ( numberOfIncorrectTries >= maxAllowedIncorrectTries ) return true;
        return false;
    } 

    //Method see if the player has won or not.
    public boolean hasWon(){
        if (this.secretWord.toString().equals(this.knownSoFar.toString())) return true;
        else return false;
    }
    
    //Method to choose a secret word from a list of words.
    public void chooseSecretWord() {
        this.secretWord = new StringBuffer();
        Random rand = new Random();

        if (gameMode == 1) {
            String[] secretWords = { "ABRUPTLY", "ABYSS", "AFFIX", "AVENUE", "BEEKEEPER", "COBWEB", "COCKINESS", 
                                    "DISAWOV", "FISHHOOK", "FOXGLOVE", "GAZEBO", "JACKPOT", "KICKSHAW", "KILOBYTE", "PAJAMA", "PIGGYBACK", 
                                    "PNEUMONIA", "SPHINX", "STRETCH", "SUBWAY", "ZIPPER", "ZODIAC", "ZOMBIE" };

            int randomIndex = rand.nextInt(secretWords.length);
            this.secretWord.append(secretWords[randomIndex]);
        }
        else if (gameMode == 2) {
            String[] secretSentences = {"THE UNEXAMINED LIFE IS NOT WORTH LIVING", "ONE CANNOT STEP TWICE IN THE SAME RIVER", "THE PRICE OF GREATNESS IS RESPONSIBILITY",
             "THE ONLY THING WE HAVE TO FEAR IS FEAR ITSELF", "WHERE THERE IS LOVE THERE IS LIFE", "THERE IS NO SIN EXCEPT STUPIDITY"};

             int randomIndex = rand.nextInt(secretSentences.length);
             this.secretWord.append(secretSentences[randomIndex]);
        }
    }
    
    //Method to reveal known letters
    public void updateKnownSoFar(String letter){
        for ( int i = 0; i < this.secretWord.length(); i++ ){
            if( Character.toString(this.secretWord.charAt(i)).equals(letter) ) {
                knownSoFar.replace(i, i+1, letter);
            }
        }
    }

    //Method to display the number of incorrect guesses of player
    public String displayTheHangman(){
        String base = "¯¯¯\n";
        String pole3 = " |   O\n";
        String pole1 = " | \n";
        String pole2 = " |   |\n";
        String ceiling = "  ___\n";
        String poleWithLeftArm = " |  /  \n";
        String poleWithBothArms = " |  / \\\n";

        String result0 = ceiling + pole1 + pole1 + pole1 + pole1 + pole1 + pole1 + base;
        String result1 = ceiling + pole2 + pole1 + pole1 + pole1 + pole1 + pole1 + base;
        String result2 = ceiling + pole2 + pole3 + pole1 + pole1 + pole1 + pole1 + base;
        String result3 = ceiling + pole2 + pole3 + poleWithLeftArm + pole1 + pole1 + pole1 + pole1 + base;
        String result4 = ceiling + pole2 + pole3 + poleWithBothArms + pole1 + pole1 + pole1 + pole1 + base;
        String result5 = ceiling + pole2 + pole3 + poleWithBothArms + pole2 + pole1 + pole1 + pole1 + base;
        String result6 = ceiling + pole2 + pole3 + poleWithBothArms + pole2 + poleWithLeftArm + pole1 + pole1 + base;
        String result7 = ceiling + pole2 + pole3 +poleWithBothArms + pole2 + poleWithBothArms + pole1 + base;
        String ultimateResult = result0;

        if( numberOfIncorrectTries == 0 ) ultimateResult = result0;
        if( numberOfIncorrectTries == 1 ) ultimateResult = result1;
        if( numberOfIncorrectTries == 2 ) ultimateResult = result2;
        if( numberOfIncorrectTries == 3 ) ultimateResult = result3;
        if( numberOfIncorrectTries == 4 ) ultimateResult = result4;
        if( numberOfIncorrectTries == 5 ) ultimateResult = result5;
        if( numberOfIncorrectTries == 6 ) ultimateResult = result6;
        if( numberOfIncorrectTries == 7 ) ultimateResult = result7;

        return ultimateResult;
    }

    //Method to update unusedLetters and usedLetters 
    public void updateUsedAndUnusedLetters( String playerGuess ){
        this.usedLetters.append(playerGuess);
        this.unusedLetters.delete(unusedLetters.indexOf(playerGuess), unusedLetters.indexOf(playerGuess) + 1);
    }

}