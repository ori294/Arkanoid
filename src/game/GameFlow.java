package game;

import java.io.File;
import java.io.IOException;
import java.util.List;

import utilities.HighScoresAnimation;
import utilities.HighScoresTable;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

/**
 * GameFlow class: Manages the flow of the game.
 *
 * @author ori29
 *
 */
public class GameFlow {

    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private HighScoresTable highscores;
    private Counter score;
    private Counter lives;
    private GUI gui;
    private Sleeper sleeper;

    /**
     * GameFlow contructor.
     * @param ar an AnimationRunner.
     * @param ks a KeyBoardSensor.
     * @param g a GUI.
     * @param s a Sleeper.
     * @param h HighScoresTable.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI g, Sleeper s,
            HighScoresTable h) {

        this.gui = g;
        this.sleeper = s;
        this.animationRunner = ar;
        this.keyboardSensor = this.gui.getKeyboardSensor();
        this.highscores = h;
        this.score = new Counter();
        this.lives = new Counter(7);
    }

    /**
     * runLevels: Gets a list of levels and run them in order.
     * @param levels a list of the game levels.
     */
    public void runLevels(List<LevelInformation> levels) {

        for (int i = 0; i < levels.size(); i++) {

            GameLevel level = new GameLevel(levels.get(i), this.score, this.lives, this.animationRunner,
                    this.keyboardSensor, this.gui, this.sleeper);
            level.initialize();

            while (this.lives.getValue() > 0 && level.blockCount() > 0) {
                level.playOneTurn();
            }

            if (this.lives.getValue() <= 0) {
                this.animationRunner.run(
                        new KeyPressStoppableAnimation(this.keyboardSensor, "space", new GameOverScreen(this.score)));
                this.saveScore();
                HighScoresAnimation hs = new HighScoresAnimation(this.highscores);
                hs.refreshHighScores();
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                        "space", hs));
                return;
            }
        }
        if (this.lives.getValue() > 0) {
            this.animationRunner
                    .run(new KeyPressStoppableAnimation(this.keyboardSensor, "space", new WinnerScreen(this.score)));
            this.saveScore();
            HighScoresAnimation hs = new HighScoresAnimation(this.highscores);
            hs.refreshHighScores();
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                    "space", hs));
            return;
        }
    }
    /**
     * Saves the score achieved during the game.
     */
    public void saveScore() {

        if (!this.highscores.isInHallOfFame(this.score.getValue())) {
            return;
        }
        File filename = new File("highscores.txt");
        DialogManager dialog = gui.getDialogManager();
        String name = dialog.showQuestionDialog("Name", "What is your name?", "");

        HighScoresTable.ScoreInfo score1 = this.highscores.new ScoreInfo(name, this.score.getValue());
        this.highscores.add(score1);
        try {
            this.highscores.save(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}