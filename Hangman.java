public class Hangman {

    private StringBuffer SecretWord;
    private StringBuffer allLetters;
    private StringBuffer unusedLetters;
    private StringBuffer usedLetters;
    private int numberOfIncorrectTries;
    private int maxAllowedIncorrectTries;
    private StringBuffer knownSoFar;

    public Hangman() {

    }

    public String getAllLetters() {
        return allLetters;
    }

    public String getUsedLetters() {
        return usedLetters;        
    }

    public String getUnusedLetters() {
        return unusedLetters;
        
    }

    public int getNumOfIncorrectTries() {
        return numberOfIncorrectTries;
    }

    public int getMaxAllowedIncorrectTries() {
        return maxAllowedIncorrectTries;        

        
    }

    public String getKnownSoFar() {
        return knownSoFar;
        
    }

    public int tryThis( char c1) {
        int count = 0;
        for (int i = 0 ; i < SecretWord.length(); i++){
            if (c1 == SecretWord.charAt(i)){
                count++;
            }
        }
        return count;
              
    }

    public boolean isGameOver() {

    }

    public boolean hasLost() {

    }

    private void chooseSecretWord() {
        
    }
}