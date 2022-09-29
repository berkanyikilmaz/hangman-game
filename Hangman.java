public class Hangman {

    private StringBuffer SecretWord;
    private StringBuffer allLetters;
    private StringBuffer unusedLetters;
    private StringBuffer usedLetters;
    private int numberOfIncorrectTries;
    private int maxAllowedIncorrectTries;
    private StringBuffer knownSoFar;
    int count = (int)Math.random()*12;

    public Hangman() {
        numberOfIncorrectTries = 0;
        maxAllowedIncorrectTries = 6;
        chooseSecretWord();
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
        usedLetters.append(letter);
        

        int occurences = 0;
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter) {
                occurences++;
                knownSofar.insert(i, letter);
                knownSofar.setLength(secretWord.length());
            } 
                

        }
        if (occurences == 0)
            numberOfIncorrectTries++;
        return occurences;
              
    }

    public boolean isGameOver() {
        if (numberOfIncorrectTries > maxAllowedIncorrectTries ||getKnownSofar().equals(secretWord.toString()))
        return true;

    else
        return false;
        }

    

    public boolean hasLost() {
        boolean lost = false;
        if (isGameOver()) {
            if (numberOfIncorrectTries > maxAllowedIncorrectTries)
                lost = true;
        }
        return lost;
        }

    

    private void chooseSecretWord() {
        secretWord.insert(0, fixedList[count]);
        for (int i = 0; i < secretWord.length(); i++) {
            knownSofar.append('-');
        }
        count++;
    }
}