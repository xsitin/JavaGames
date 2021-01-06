class Game {
    String Word;
    String Remainder;
    int maxLives;
    private int Lives;

    public Game(String word, int lives) {
        Word = word;
        Remainder = word.charAt(0) +
                "_".repeat(Math.max(0, word.length() - 2)) +
                word.charAt(word.length() - 1);
        Lives = lives;
        maxLives = lives;
    }

    public Result TryGuess(char letter) {
        char[] remainder = Remainder.toCharArray();
        String wordGuessed = Remainder;
        for (int i = 0; i < Word.length(); i++)
            if (letter == Word.charAt(i)) {
                remainder[i] = letter;
                wordGuessed = String.valueOf(remainder);
            }
        if (wordGuessed.equals(Remainder)) {
            Lives--;
            return Lives > 0 ? Result.NotGuessed : Result.Lose;
        }
        Remainder = wordGuessed;
        return wordGuessed.equals(Word) ? Result.Win : Result.Guessed;
    }

    public String GetRemainder() {
        return Remainder;
    }

    public int GetLives() {
        return Lives;
    }

    public int GetMaxLives() {
        return maxLives;
    }

}