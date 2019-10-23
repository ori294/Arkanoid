package utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.List;

import game.AnimationRunner;
import game.GameFlow;
import game.LevelInformation;
import levels.LevelSpecificationReader;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

/**
 * PlayGameTask a menu task that will run the game.
 * @author ori29
 */
public class PlayGameTask implements Task<Void> {

    private int levelSet;
    private AnimationRunner animation;
    private KeyboardSensor keyboard;
    private GUI gui;
    private Sleeper sleeper;
    private String filePath;
    private HighScoresTable highscoresTable;

    /**
     * PlayGameTask constructor.
     * @param ar Animation runner.
     * @param ks Keyboard Sensor.
     * @param g GUI.
     * @param s Sleeper.
     * @param hs HighScoreTable.
     * @param lvlSet the number of level set that we want to play.
     * @param path a relative file path.
     */
    public PlayGameTask(AnimationRunner ar, KeyboardSensor ks, GUI g, Sleeper s, HighScoresTable hs,
            int lvlSet, String path) {
        this.animation = ar;
        this.keyboard = ks;
        this.gui = g;
        this.sleeper = s;
        this.highscoresTable = hs;
        this.levelSet = lvlSet;
        this.filePath = path;
    }

    /**
     * run: runs the game.
     * @return Void.
     */
    @Override
    public Void run() {

            try {
                BufferedReader br = null;
                InputStream fis = null;

                if (this.filePath == null) {
                    fis = ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt");
                    br = new BufferedReader(new InputStreamReader(fis));
                } else {
                    fis = ClassLoader.getSystemClassLoader().getResourceAsStream(this.filePath);
                    br = new BufferedReader(new InputStreamReader(fis));
                }

                LineNumberReader lnr = new LineNumberReader(br);
                String fn = null;

                while (lnr.getLineNumber() != this.levelSet * 2) {
                    fn = lnr.readLine();
                }

                lnr.close();

                LevelSpecificationReader levelSp = new LevelSpecificationReader();
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(fn);
                BufferedReader bfr = new BufferedReader(new InputStreamReader(is));

                List<LevelInformation> levels = levelSp.fromReader(bfr);

                GameFlow theGame = new GameFlow(animation, keyboard, gui, sleeper, highscoresTable);
                theGame.runLevels(levels);

            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
    }
}
