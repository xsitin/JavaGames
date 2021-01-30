class Game {

    public Game(String word, int lives) {

    }

    public Result TryGuess(char letter) {
        return Result.Lose;
    }

    public String GetRemainder() {
        return "";
    }

    public int GetLives() {
        return 0;
    }

    public int GetMaxLives() {
        return 0;
    }

}